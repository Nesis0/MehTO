package com.isen.mehto.data.models

data class SingleDayForecastResponse(
    val weather: List<WeatherCondition>,
    val main: Infos,
    var name: String,
    val dt_txt: String
)

data class WeatherCondition(
    val main: String
)

data class Infos(
    val temp: Double,
    val humidity: Int
)