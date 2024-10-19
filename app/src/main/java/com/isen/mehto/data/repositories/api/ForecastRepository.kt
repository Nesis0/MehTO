package com.isen.mehto.data.repositories.api

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Forecast
import com.isen.mehto.data.models.Location

interface ForecastRepository {
    suspend fun getTodayWeather(position: Position): Forecast
    suspend fun getForecast(position: Position): List<Forecast>
    suspend fun getLocationFromName(name: String): List<Location>
}