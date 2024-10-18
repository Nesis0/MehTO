package com.isen.mehto.data.repositories.impl

import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.weather.api.WeatherServiceImpl

class WeatherRepositoryImpl(private val weatherService: WeatherServiceImpl) : WeatherRepository {
    override suspend fun getTodayWeather(position: Position): Weather {
        return weatherService.getTodayWeather(position.lat, position.lon)
    }

    override suspend fun getForecast(position: Position): Weather {
        return weatherService.getForecast(position.lat, position.lon)
    }

    override suspend fun getCoordinatesFromCity(city: String): List<Position> {
        return weatherService.getCoordinatesFromCity(city)
    }

    private fun mapWeatherFromWeatherResponse(weatherResponse: WeatherResponse): Weather{
        return Weather(weatherResponse.name, Temperature.fromCelsius(weatherResponse.main.temp), getWeatherTypeFromResponseWeatherType(weatherResponse.weather[0].main) ,weatherResponse.main.humidity)
    }
}