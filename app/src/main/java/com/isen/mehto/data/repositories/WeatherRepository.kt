package com.isen.mehto.data.repositories

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import java.time.OffsetDateTime

interface WeatherRepository {
    suspend fun getTodayWeather(position: Position): Weather
    suspend fun getForecast(position: Position): Weather
    suspend fun getCoordinatesFromCity(city: String): Position
}