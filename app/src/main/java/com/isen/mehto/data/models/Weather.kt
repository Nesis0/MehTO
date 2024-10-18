package com.isen.mehto.data.models

import android.health.connect.datatypes.units.Percentage
import android.health.connect.datatypes.units.Temperature
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