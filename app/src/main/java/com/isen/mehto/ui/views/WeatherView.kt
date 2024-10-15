package com.isen.mehto.ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.ui.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen() {
    val viewModel: WeatherViewModel = viewModel()

    val city = viewModel.weather.value.city ?: "Unknown location"
    val temperature = viewModel.weather.value.temperature
    val weatherType = viewModel.weather.value.weatherType
    val rainRisk = viewModel.weather.value.rainRisk

    Text(text = city)
}
