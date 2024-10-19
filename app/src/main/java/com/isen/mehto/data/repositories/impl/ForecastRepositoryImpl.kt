package com.isen.mehto.data.repositories.impl

import com.isen.mehto.data.models.FiveDaysForecastResponse
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Temperature
import com.isen.mehto.data.models.TemperatureUnit
import com.isen.mehto.data.models.Forecast
import com.isen.mehto.data.models.SingleDayForecastResponse
import com.isen.mehto.data.models.WeatherConditions
import com.isen.mehto.data.repositories.ForecastRepository
import com.isen.mehto.weather.api.WeatherServiceImpl

class ForecastRepositoryImpl(private val weatherService: WeatherServiceImpl) : ForecastRepository {
    override suspend fun getTodayWeather(position: Position): Forecast {
        val singleDayForecastResponse: SingleDayForecastResponse = weatherService.getTodayWeather(position.lat, position.lon)
        val forecast: Forecast = mapWeatherFromWeatherResponse(singleDayForecastResponse)
        return forecast
    }

    override suspend fun getForecast(position: Position): List<Forecast> {
        val fiveDaysForecastResponse: FiveDaysForecastResponse = weatherService.getForecast(position.lat, position.lon)
        val forecastList: MutableList<Forecast> = mapWeatherListFromForecastResponse(fiveDaysForecastResponse)
        return forecastList
    }

    override suspend fun getCoordinatesFromCity(city: String): List<Position> {
        return weatherService.getCoordinatesFromCity(city)
    }

    private fun mapWeatherListFromForecastResponse(fiveDaysForecastResponse: FiveDaysForecastResponse): MutableList<Forecast> {
        val forecastList: MutableList<Forecast> = mutableListOf()
        for (weatherInfos in fiveDaysForecastResponse.list){
            weatherInfos.name = fiveDaysForecastResponse.city.name
            if (isNoonForecast(weatherInfos.dt_txt))
                forecastList.add(mapWeatherFromWeatherResponse(weatherInfos))
        }
        return forecastList
    }

    private fun isNoonForecast(dt: String): Boolean {
        return dt.contains("12:00:00")
    }

    private fun mapWeatherFromWeatherResponse(singleDayForecastResponse: SingleDayForecastResponse): Forecast{
        return Forecast(singleDayForecastResponse.name, Temperature(singleDayForecastResponse.main.temp, TemperatureUnit.KELVIN), getWeatherTypeFromResponseWeatherType(singleDayForecastResponse.weather[0].main) ,singleDayForecastResponse.main.humidity)
    }

    private fun getWeatherTypeFromResponseWeatherType(weatherType: String): WeatherConditions {
        return when (weatherType){
            "Clear" -> WeatherConditions.SUNNY
            "Clouds" -> WeatherConditions.CLOUDY
            "Rain" -> WeatherConditions.RAINY
            "Snow" -> WeatherConditions.SNOWY
            "ThunderStorm" -> WeatherConditions.STORMY
            "Drizzle" -> WeatherConditions.DRIZZLY

            else -> WeatherConditions.UNKNOWN
        }
    }
}