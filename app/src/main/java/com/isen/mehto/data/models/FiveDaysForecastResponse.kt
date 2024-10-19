package com.isen.mehto.data.models

data class FiveDaysForecastResponse(
    val list: List<SingleDayForecastResponse>,
    val city: City
)

data class City(
    val name: String
)