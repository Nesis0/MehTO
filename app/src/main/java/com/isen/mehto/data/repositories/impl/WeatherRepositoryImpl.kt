package com.isen.mehto.data.repositories.impl

import android.health.connect.datatypes.units.Temperature
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.models.WeatherResponse
import com.isen.mehto.data.models.WeatherType
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.weather.api.WeatherServiceImpl

class WeatherRepositoryImpl(private val weatherService: WeatherServiceImpl) : WeatherRepository {
    override suspend fun getTodayWeather(position: Position): Weather {
        val weatherResponse: WeatherResponse = weatherService.getTodayWeather(position.lat, position.lon)
        val weather: Weather = mapWeatherFromWeatherResponse(weatherResponse)

        return weather
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

    private fun getWeatherTypeFromResponseWeatherType(weatherType: String): WeatherType {
        return when (weatherType){
            "Clear" -> WeatherType.SUNNY
            "Clouds" -> WeatherType.CLOUDY
            "Rain" -> WeatherType.RAINY
            "Snow" -> WeatherType.SNOWY
            "ThunderStorm" -> WeatherType.STORMY
            "Drizzle" -> WeatherType.DRIZZLY

            else -> WeatherType.UNKNOWN
        }
    }
}