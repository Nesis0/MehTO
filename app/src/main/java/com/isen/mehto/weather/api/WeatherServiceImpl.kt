package com.isen.mehto.weather.api

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather

class WeatherServiceImpl: WeatherService {
    override suspend fun getTodayWeather(latitude: Float, longitude: Float): Weather {
        return WeatherCall.service.getTodayWeather(latitude, longitude)
    }

    override suspend fun getCoordinatesFromCity(city: String, country: String): Position {
        return WeatherCall.service.getCoordinatesFromCity(city, country)
    }
}