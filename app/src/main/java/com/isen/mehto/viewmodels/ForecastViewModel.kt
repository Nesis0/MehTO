package com.isen.mehto.viewmodels

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
import com.isen.mehto.data.repositories.api.forecast.ForecastRepository
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
    private val _sliderPosition: MutableIntState = mutableIntStateOf(0)
    val sliderPosition = _sliderPosition
    private val _favorites = mutableStateOf(listOf<FavoriteLocation>())
    val favorites = _favorites

    init {
        viewModelScope.launch {
            configRepository.initConfig()
            temperatureUnit.value = TemperatureUnit.valueOf(configRepository.read(ConfigType.UNIT.toString()) ?: "")
            favorites.value = favoriteLocationRepository.getAllLocations()
            updateRender()
        }
    }

    private suspend fun updateRender() {
        val position: Position
        if (_sliderPosition.intValue == 0)
            position = getEffectivePosition()
        else{
            val favoriteIndex = _sliderPosition.intValue - 1
            val newPosition = favorites.value[favoriteIndex]
            position = Position(newPosition.latitude, newPosition.longitude)
        }

        _currentForecast.value = forecastRepository.getTodayWeather(position)
        _forecastWeek.value = forecastRepository.getForecast(position)
    }

    private suspend fun getEffectivePosition(): Position {
        val isFavoritePreferred: Boolean = !configRepository.read(ConfigType.ENABLE_GEOLOCATION.toString()).toBoolean()
        val isFavoriteEmpty = favorites.value.isEmpty()

        if (isFavoritePreferred && !isFavoriteEmpty)
            return getPreferredLocationPosition()

        if (!isFavoritePreferred && geolocationRepository.isGeolocationPermitted())
            return geolocationRepository.getLastPosition()

        return Position(0.0, 0.0)
    }

    private fun getPreferredLocationPosition(): Position {
        val preferredLocation: List<FavoriteLocation> = favorites.value.sortedBy { it.preference_index }
        return Position(preferredLocation[0].latitude, preferredLocation[0].longitude)
    }

    fun sliderChangePosition(newValue: Int){
        viewModelScope.launch {
            _sliderPosition.intValue = newValue
            updateRender()
        }
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
