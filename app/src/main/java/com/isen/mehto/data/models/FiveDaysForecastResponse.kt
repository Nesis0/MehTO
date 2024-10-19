package com.isen.mehto.data.models

data class FiveDaysForecastResponse(
    val list: List<WeatherResponse>,
    val city: City
)

data class City(
    val name: String
)