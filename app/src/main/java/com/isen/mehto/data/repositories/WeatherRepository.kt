package com.isen.mehto.data.repositories

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import java.time.OffsetDateTime

interface WeatherRepository {
    suspend fun getTodayWeather(position: Position): Weather
    suspend fun getWeather(position: Position, time: OffsetDateTime): Weather
    suspend fun getWeather(position: Position, start: OffsetDateTime, end: OffsetDateTime): List<Weather>
}