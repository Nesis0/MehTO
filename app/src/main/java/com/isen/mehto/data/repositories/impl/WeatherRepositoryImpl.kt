package com.isen.mehto.data.repositories.impl

import com.isen.mehto.data.models.ForecastResponse
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Temperature
import com.isen.mehto.data.models.TemperatureUnit
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

    override suspend fun getForecast(position: Position): List<Weather> {
        val forecastResponse: ForecastResponse = weatherService.getForecast(position.lat, position.lon)
        val weatherList: MutableList<Weather> = mapWeatherListFromForecastResponse(forecastResponse)
        return weatherList
    }

    override suspend fun getCoordinatesFromCity(city: String): List<Position> {
        return weatherService.getCoordinatesFromCity(city)
    }

    private fun mapWeatherListFromForecastResponse(forecastResponse: ForecastResponse): MutableList<Weather> {
        val weatherList: MutableList<Weather> = mutableListOf()
        for (weatherInfos in forecastResponse.list){
            weatherInfos.name = forecastResponse.city.name
            weatherList.add(mapWeatherFromWeatherResponse(weatherInfos))
        }
        return weatherList
    }

    private fun mapWeatherFromWeatherResponse(weatherResponse: WeatherResponse): Weather{
        return Weather(weatherResponse.name, Temperature(weatherResponse.main.temp, TemperatureUnit.KELVIN), getWeatherTypeFromResponseWeatherType(weatherResponse.weather[0].main) ,weatherResponse.main.humidity)
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