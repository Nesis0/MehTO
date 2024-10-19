package com.isen.mehto.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.OfflineConfigRepository
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Forecast
import com.isen.mehto.data.repositories.ForecastRepository
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val forecastRepository: ForecastRepository,
    private val configRepository: OfflineConfigRepository
) : ViewModel() {
    private val _currentForecast: MutableState<Forecast?> = mutableStateOf(null)
    val currentWeather = _currentForecast
    private val _forecastWeek: MutableState<List<Forecast>> = mutableStateOf(listOf())
    val weatherWeek = _forecastWeek

    init {
        viewModelScope.launch {
            val positions: List<Position> = getEffectivePosition()
            //TODO("Get the user's choice between possibility")
            _currentForecast.value = forecastRepository.getTodayWeather(positions[0])

            val next5DaysForecast: List<Forecast> = forecastRepository.getForecast(positions[0])
        }
    }

    private suspend fun getEffectivePosition(): List<Position> {
        val configs = configRepository.read("")

        //TODO("Get position depending on settings' favorite (default current pos)")
        return forecastRepository.getCoordinatesFromCity("Belfort") // ❤️❤️❤️
    }

    class ViewModelFactory(
        private val forecastRepository: ForecastRepository,
        private val configRepository: OfflineConfigRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
                return ForecastViewModel(
                    forecastRepository = forecastRepository,
                    configRepository = configRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
