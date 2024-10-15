package com.isen.mehto.data.models

import android.health.connect.datatypes.units.Percentage
import android.health.connect.datatypes.units.Temperature

enum class WeatherType {
    SUNNY,
    CLOUDY,
    RAINY,
    STORMY,
    SNOWY
}

data class Weather(
    val city: String?,
    val temperature: Temperature?,
    val weatherType: WeatherType?,
    val rainRisk: Percentage?
)
