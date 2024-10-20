package com.isen.mehto.data.models

import android.health.connect.datatypes.units.Percentage
import com.isen.mehto.R

enum class WeatherConditions(val image: Int) {
    SUNNY(R.drawable.ic_sunny),
    CLOUDY(R.drawable.ic_cloudy),
    DRIZZLY(R.drawable.ic_drizzly),
    RAINY(R.drawable.ic_rainy),
    STORMY(R.drawable.ic_stormy),
    SNOWY(R.drawable.ic_snowy),
    UNKNOWN(R.drawable.ic_cloudy)
}

data class Forecast(
    val city: String,
    val temperature: Temperature,
    val weatherConditions: WeatherConditions,
    val humidity: Percentage
)

