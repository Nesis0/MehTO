package com.isen.mehto.data.models


import com.isen.mehto.R

enum class WeatherConditions(val image: Int) {
    SUNNY(R.drawable.ic_sunny),
    CLOUDY(R.drawable.ic_cloudy),
    DRIZZLY(-1), //TODO
    RAINY(R.drawable.ic_rainy),
    STORMY(R.drawable.ic_stormy),
    SNOWY(-1), //TODO
    UNKNOWN(-1) //TODO
}

data class Forecast(
    val city: String,
    val temperature: Temperature,
    val weatherConditions: WeatherConditions,
    val rainRisk: Int
)

