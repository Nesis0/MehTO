package com.isen.mehto.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Forecast
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

    init {
        viewModelScope.launch {
            configRepository.initConfig()
            val position: Position = getEffectivePosition()
            _currentForecast.value = forecastRepository.getTodayWeather(position)
            _forecastWeek.value = forecastRepository.getForecast(position)
        }
    }

    private suspend fun getEffectivePosition(): Position {
        val isFavoritePreferred: Boolean = configRepository.read("geolocation").toBoolean()
        val isFavoriteEmpty = favoriteLocationRepository.getAllLocations().isEmpty()

        return if (isFavoritePreferred && !isFavoriteEmpty)
            getPreferredLocationPosition()
        else
            forecastRepository.getPositionsFromCityName("Belfort")[0] //TODO("retrieve position from geo localisation")

    }

    private suspend fun getPreferredLocationPosition(): Position {
        val preferredLocation: FavoriteLocation = favoriteLocationRepository.readByIndex("1")
        return Position(preferredLocation.latitude, preferredLocation.longitude)
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
