package com.isen.mehto.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.OfflineConfigRepository
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.repositories.WeatherRepository
import kotlinx.coroutines.launch
import java.time.OffsetDateTime

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val configRepository: OfflineConfigRepository
) : ViewModel() {
    private val _currentWeather: MutableState<Weather?> = mutableStateOf(null)
    val currentWeather = _currentWeather
    private val _weatherWeek: MutableState<List<Weather>> = mutableStateOf(listOf())
    val weatherWeek = _weatherWeek

    init {
        viewModelScope.launch {
            val position = getEffectivePosition()

            _currentWeather.value = weatherRepository.getWeather(position)

            val start = OffsetDateTime.now().plusDays(1)
            val end = start.plusDays(6)
            _weatherWeek.value = weatherRepository.getWeather(position, start, end)
        }
    }

    private suspend fun getEffectivePosition(): Position {
        val configs = configRepository.read("")

        TODO("Get position depending on settings' favorite (default current pos)")
    }

    class ViewModelFactory(
        private val weatherRepository: WeatherRepository,
        private val configRepository: OfflineConfigRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                return WeatherViewModel(
                    weatherRepository = weatherRepository,
                    configRepository = configRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
