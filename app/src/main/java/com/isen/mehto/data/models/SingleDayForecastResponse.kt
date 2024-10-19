package com.isen.mehto.data.models

data class SingleDayForecastResponse(
    val weather: List<ForecastInfos>,
    val main: Infos,
    var name: String,
    val dt_txt: String
)

data class ForecastInfos(
    val main: String
)

data class Infos(
    val temp: Double,
    val humidity: Int
)