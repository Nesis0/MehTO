package com.isen.mehto.data.models

data class WeatherResponse(
    val weather: List<WeatherCondition>,
    val main: Infos,
    val name: String,
)

data class WeatherCondition(val main: String)

data class Infos(
    val temp: Double,
    val humidity: Int,
)