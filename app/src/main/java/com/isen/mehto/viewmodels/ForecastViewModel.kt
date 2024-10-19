package com.isen.mehto.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Forecast
import com.isen.mehto.data.repositories.api.ForecastRepository
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val forecastRepository: ForecastRepository,
    private val configRepository: OfflineConfigRepository,
    private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
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
        val settings = configRepository.read("")

        val isFavoritePreferred = true //TODO("Retrieve value from settings")

        if (!isFavoritePreferred)
         return forecastRepository.getCoordinatesFromCity("Belfort")[0] //TODO("retrieve position from geo localisation")
        else {
            val primaryLocation = favoriteLocationRepository.getAllLocations()[0]
            return Position(primaryLocation.longitude, primaryLocation.latitude)
        }
    }

    class ViewModelFactory(
        private val forecastRepository: ForecastRepository,
        private val configRepository: OfflineConfigRepository,
        private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
                return ForecastViewModel(
                    forecastRepository = forecastRepository,
                    configRepository = configRepository,
                    favoriteLocationRepository = favoriteLocationRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
