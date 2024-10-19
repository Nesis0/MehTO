package com.isen.mehto.data.forecast.api

import com.isen.mehto.data.models.FiveDaysForecastResponse
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.SingleDayForecastResponse

class WeatherServiceImpl: ForecastService {
    override suspend fun getTodayWeather(latitude: Float, longitude: Float, apiKey: String?): SingleDayForecastResponse {
        return ForecastApi.service.getTodayWeather(latitude, longitude)
    }

    override suspend fun getForecast(latitude: Float, longitude: Float, apiKey: String?): FiveDaysForecastResponse {
        return ForecastApi.service.getForecast(latitude, longitude)
    }

    override suspend fun getCoordinatesFromCity(city: String, limit: Int?, apiKey: String?): List<Position> {
        return ForecastApi.service.getCoordinatesFromCity(city)
    }
}