package com.isen.mehto.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.data.repositories.impl.WeatherRepositoryImpl
import kotlinx.coroutines.launch
import java.time.OffsetDateTime

class WeatherViewModel() : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl();

    private val _currentWeather: MutableState<Weather?> = mutableStateOf(null)
    val currentWeather = _currentWeather
    private val _weatherWeek: MutableState<List<Weather>> = mutableStateOf(listOf())
    val weatherWeek = _weatherWeek

    init {
        viewModelScope.launch {
            _currentWeather.value = weatherRepository.getWeather()

            val start = OffsetDateTime.now().plusDays(1)
            val end = start.plusDays(6)
            _weatherWeek.value = weatherRepository.getWeather(start, end)
        }
    }
}
