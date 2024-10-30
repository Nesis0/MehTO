package com.isen.mehto.data.forecast.api.forecast

import com.isen.mehto.data.models.FiveDaysForecastResponse
import com.isen.mehto.data.models.Location
import com.isen.mehto.data.models.SingleDayForecastResponse

class WeatherServiceImpl: ForecastService {
    override suspend fun getTodayWeather(latitude: Double, longitude: Double, apiKey: String?): SingleDayForecastResponse {
        return ForecastApi.service.getTodayWeather(latitude, longitude)
    }

    override suspend fun getForecast(latitude: Double, longitude: Double, apiKey: String?): FiveDaysForecastResponse {
        return ForecastApi.service.getForecast(latitude, longitude)
    }

    override suspend fun getLocationFromName(name: String, limit: Int?, apiKey: String?): List<Location> {
        return ForecastApi.service.getLocationFromName(name)
    }
}