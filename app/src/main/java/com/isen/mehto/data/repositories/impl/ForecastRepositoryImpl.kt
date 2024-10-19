package com.isen.mehto.data.repositories.impl

import com.isen.mehto.data.models.FiveDaysForecastResponse
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Temperature
import com.isen.mehto.data.models.TemperatureUnit
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.models.SingleDayForecastResponse
import com.isen.mehto.data.models.WeatherType
import com.isen.mehto.data.repositories.ForecastRepository
import com.isen.mehto.weather.api.WeatherServiceImpl

class ForecastRepositoryImpl(private val weatherService: WeatherServiceImpl) : ForecastRepository {
    override suspend fun getTodayWeather(position: Position): Weather {
        val singleDayForecastResponse: SingleDayForecastResponse = weatherService.getTodayWeather(position.lat, position.lon)
        val weather: Weather = mapWeatherFromWeatherResponse(singleDayForecastResponse)
        return weather
    }

    override suspend fun getForecast(position: Position): List<Weather> {
        val fiveDaysForecastResponse: FiveDaysForecastResponse = weatherService.getForecast(position.lat, position.lon)
        val weatherList: MutableList<Weather> = mapWeatherListFromForecastResponse(fiveDaysForecastResponse)
        return weatherList
    }

    override suspend fun getCoordinatesFromCity(city: String): List<Position> {
        return weatherService.getCoordinatesFromCity(city)
    }

    private fun mapWeatherListFromForecastResponse(fiveDaysForecastResponse: FiveDaysForecastResponse): MutableList<Weather> {
        val weatherList: MutableList<Weather> = mutableListOf()
        for (weatherInfos in fiveDaysForecastResponse.list){
            weatherInfos.name = fiveDaysForecastResponse.city.name
            if (isNoonForecast(weatherInfos.dt_txt))
                weatherList.add(mapWeatherFromWeatherResponse(weatherInfos))
        }
        return weatherList
    }

    private fun isNoonForecast(dt: String): Boolean {
        return dt.contains("12:00:00")
    }

    private fun mapWeatherFromWeatherResponse(singleDayForecastResponse: SingleDayForecastResponse): Weather{
        return Weather(singleDayForecastResponse.name, Temperature(singleDayForecastResponse.main.temp, TemperatureUnit.KELVIN), getWeatherTypeFromResponseWeatherType(singleDayForecastResponse.weather[0].main) ,singleDayForecastResponse.main.humidity)
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