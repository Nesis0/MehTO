package com.isen.mehto.weather.api

class WeatherServiceImpl: WeatherService {
    override suspend fun getTodayWeather(latitude: String, longitude: String): CityInfo {
        return WeatherCall.service.getTodayWeather(latitude, longitude)
    }

    override suspend fun getCoordinatesFromCity(city: String, country: String): Coordinates {
        return WeatherCall.service.getCoordinatesFromCity(city, country)
    }
}