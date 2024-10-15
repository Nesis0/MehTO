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
    private val _weatherWeek: MutableState<Array<Weather?>> = mutableStateOf(arrayOfNulls(6))
    val weatherWeek = _weatherWeek

    init {
        viewModelScope.launch {
            _currentWeather.value = weatherRepository.getWeather()

            for (i in _weatherWeek.value.indices) {
                val date = OffsetDateTime.now().plusDays(i.toLong())
                _weatherWeek.value[i] = weatherRepository.getWeather(date)
            }
        }
    }
}
