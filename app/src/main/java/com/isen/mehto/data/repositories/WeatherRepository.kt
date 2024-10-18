package com.isen.mehto.data.repositories

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather

interface WeatherRepository {
    suspend fun getTodayWeather(position: Position): Weather
    suspend fun getForecast(position: Position): List<Weather>
    suspend fun getCoordinatesFromCity(city: String): List<Position>
}