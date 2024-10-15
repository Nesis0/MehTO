package com.isen.mehto.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.data.repositories.impl.WeatherRepositoryImpl
import kotlinx.coroutines.launch

class WeatherViewModel() : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl();

    private val _weather: MutableState<Weather?> = mutableStateOf(null)
    val weather = _weather

    init {
        viewModelScope.launch {
            _weather.value = weatherRepository.getWeather()
        }
    }
}
