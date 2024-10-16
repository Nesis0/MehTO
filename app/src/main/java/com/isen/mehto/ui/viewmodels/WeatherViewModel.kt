package com.isen.mehto.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.ConfigRepository
import com.isen.mehto.data.OfflineConfigRepository
import com.isen.mehto.data.entity.ConfigDAO
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.data.repositories.impl.WeatherRepositoryImpl
import kotlinx.coroutines.launch
import java.time.OffsetDateTime

class WeatherViewModel() : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    private val position: Position = getChosenPosition()

    private fun getChosenPosition(): Position {
       TODO("Get position depending on settings' favorite (default current pos)")
    }

    private val _currentWeather: MutableState<Weather?> = mutableStateOf(null)
    val currentWeather = _currentWeather
    private val _weatherWeek: MutableState<List<Weather>> = mutableStateOf(listOf())
    val weatherWeek = _weatherWeek

    init {
        viewModelScope.launch {
            _currentWeather.value = weatherRepository.getWeather(position)

            val start = OffsetDateTime.now().plusDays(1)
            val end = start.plusDays(6)
            _weatherWeek.value = weatherRepository.getWeather(position, start, end)
        }
    }
}
