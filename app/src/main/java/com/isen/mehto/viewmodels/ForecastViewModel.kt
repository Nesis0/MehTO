package com.isen.mehto.viewmodels

import android.location.Location
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.entity.ConfigType
import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Forecast
import com.isen.mehto.data.models.TemperatureUnit
import com.isen.mehto.data.repositories.api.ForecastRepository
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import com.isen.mehto.data.repositories.geolocation.impl.GeolocationRepositoryImpl
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val forecastRepository: ForecastRepository,
    private val configRepository: OfflineConfigRepository,
    private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
    private val geolocationRepository: GeolocationRepositoryImpl
) : ViewModel() {
    private val _currentForecast: MutableState<Forecast?> = mutableStateOf(null)
    val currentWeather = _currentForecast
    private val _forecastWeek: MutableState<List<Forecast>> = mutableStateOf(listOf())
    val weatherWeek = _forecastWeek
    private val _temperatureUnit: MutableState<TemperatureUnit> = mutableStateOf(TemperatureUnit.CELSIUS)
    val temperatureUnit = _temperatureUnit
    private val _postion: MutableState<Position> = mutableStateOf(Position(0.0,0.0))
    val postion = _postion
    private val _sliderPosition: MutableIntState = mutableIntStateOf(0)
    val sliderPsotion = _sliderPosition

    init {
        viewModelScope.launch {
            configRepository.initConfig()
            val position: Position = getEffectivePosition()
            _currentForecast.value = forecastRepository.getTodayWeather(position)
            _forecastWeek.value = forecastRepository.getForecast(position)
            temperatureUnit.value = TemperatureUnit.valueOf(configRepository.read(ConfigType.UNIT.toString()) ?: "")
        }
    }

    private suspend fun getEffectivePosition(): Position {
        val isFavoritePreferred: Boolean = !configRepository.read(ConfigType.ENABLE_GEOLOCATION.toString()).toBoolean()
        val isFavoriteEmpty = favoriteLocationRepository.getAllLocations().isEmpty()

        if (isFavoritePreferred && !isFavoriteEmpty)
            return getPreferredLocationPosition()

        if (!isFavoritePreferred && geolocationRepository.isGeolocationPermitted())
            return getGeolocationPosition()

        return Position(0.0, 0.0)
    }

    private suspend fun getGeolocationPosition(): Position {
        val geolocationPos = Position(0.0, 0.0)
        geolocationRepository.getLastLocation { location: Location? ->
            if (location != null) {
                geolocationPos.lat = location.latitude
                geolocationPos.lon = location.longitude
            }
        }
        return geolocationPos
    }

    private suspend fun getPreferredLocationPosition(): Position {
        val preferredLocation: List<FavoriteLocation> = favoriteLocationRepository.getAllLocations().sortedBy { it.preference_index }
        return Position(preferredLocation[0].latitude, preferredLocation[0].longitude)
    }

    class ViewModelFactory(
        private val forecastRepository: ForecastRepository,
        private val configRepository: OfflineConfigRepository,
        private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
        private val geolocationRepository: GeolocationRepositoryImpl
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
                return ForecastViewModel(
                    forecastRepository = forecastRepository,
                    configRepository = configRepository,
                    favoriteLocationRepository = favoriteLocationRepository,
                    geolocationRepository = geolocationRepository
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
