package com.isen.mehto.data.models


import com.isen.mehto.R

enum class WeatherType(val image: Int) {
    SUNNY(R.drawable.ic_sunny),
    CLOUDY(R.drawable.ic_cloudy),
    DRIZZLY(-1), //TODO
    RAINY(R.drawable.ic_rainy),
    STORMY(R.drawable.ic_stormy),
    SNOWY(-1), //TODO
    UNKNOWN(-1) //TODO
}

data class Weather(
    val city: String,
    val temperature: Temperature,
    val weatherType: WeatherType,
    val rainRisk: Int
)

