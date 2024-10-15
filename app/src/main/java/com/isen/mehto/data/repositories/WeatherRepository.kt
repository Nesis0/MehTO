package com.isen.mehto.data.repositories

import com.isen.mehto.data.models.Weather
import java.time.OffsetDateTime

interface WeatherRepository {
    suspend fun getWeather(): Weather
    suspend fun getWeather(time: OffsetDateTime): Weather
}