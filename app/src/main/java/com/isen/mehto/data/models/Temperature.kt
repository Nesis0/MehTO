package com.isen.mehto.data.models

import android.annotation.SuppressLint

enum class TemperatureUnit {
    KELVIN,
    CELSIUS,
    FAHRENHEIT
}

class Temperature(private val value: Double, private val unit: TemperatureUnit){
    fun toCelsius(): Double {
        return when(this.unit){
            TemperatureUnit.KELVIN -> value - 273.15
            TemperatureUnit.FAHRENHEIT -> (this.value - 32) * 5 / 9
            else -> this.value
        }
    }

    fun toFahrenheit(): Double {
        return when (this.unit) {
            TemperatureUnit.CELSIUS -> (this.value * 9 / 5) + 32
            TemperatureUnit.KELVIN -> (this.value - 273.15) * 9 / 5 + 32
            else -> this.value
        }
    }

    fun toKelvin(): Double {
        return when (this.unit) {
            TemperatureUnit.CELSIUS -> this.value + 273.15
            TemperatureUnit.FAHRENHEIT -> (this.value - 32) * 5 / 9 + 273.15
            else -> this.value
        }
    }

    override fun toString(): String {
        return "${this.value}"
    }

    @SuppressLint("DefaultLocale")
    fun format(unit: TemperatureUnit): String {
        val value = when (unit) {
            TemperatureUnit.KELVIN -> toKelvin()
            TemperatureUnit.CELSIUS -> toCelsius()
            TemperatureUnit.FAHRENHEIT -> toFahrenheit()
        }

        val sign = when (unit) {
            TemperatureUnit.KELVIN -> "K"
            TemperatureUnit.CELSIUS -> "°C"
            TemperatureUnit.FAHRENHEIT -> "°F"
        }

        return "${String.format("%.2f", value)} $sign"
    }
}