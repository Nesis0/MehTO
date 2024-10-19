package com.isen.mehto.weather.api

import com.isen.mehto.data.models.FiveDaysForecastResponse
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.WeatherResponse

class WeatherServiceImpl: ForecastService {
    override suspend fun getTodayWeather(latitude: Float, longitude: Float, apiKey: String?): WeatherResponse {
        return ForecastApi.service.getTodayWeather(latitude, longitude)
    }

    override suspend fun getForecast(latitude: Float, longitude: Float, apiKey: String?): FiveDaysForecastResponse {
        return ForecastApi.service.getForecast(latitude, longitude)
    }

    override suspend fun getCoordinatesFromCity(city: String, limit: Int?, apiKey: String?): List<Position> {
        return ForecastApi.service.getCoordinatesFromCity(city)
    }
}