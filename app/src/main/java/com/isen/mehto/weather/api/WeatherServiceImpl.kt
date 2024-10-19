package com.isen.mehto.weather.api

import com.isen.mehto.data.models.ForecastResponse
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.WeatherResponse

class WeatherServiceImpl: WeatherService {
    override suspend fun getTodayWeather(latitude: Float, longitude: Float, apiKey: String?): WeatherResponse {
        return ForecastApi.service.getTodayWeather(latitude, longitude)
    }

    override suspend fun getForecast(latitude: Float, longitude: Float, apiKey: String?): ForecastResponse {
        return ForecastApi.service.getForecast(latitude, longitude)
    }

    override suspend fun getCoordinatesFromCity(city: String, limit: Int?, apiKey: String?): List<Position> {
        return ForecastApi.service.getCoordinatesFromCity(city)
    }
}