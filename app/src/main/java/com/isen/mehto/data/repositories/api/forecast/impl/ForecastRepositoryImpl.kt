package com.isen.mehto.data.repositories.api.forecast.impl

import android.health.connect.datatypes.units.Percentage
import com.isen.mehto.data.models.FiveDaysForecastResponse
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Temperature
import com.isen.mehto.data.models.TemperatureUnit
import com.isen.mehto.data.models.Forecast
import com.isen.mehto.data.models.SingleDayForecastResponse
import com.isen.mehto.data.models.WeatherConditions
import com.isen.mehto.data.repositories.api.forecast.ForecastRepository
import com.isen.mehto.data.forecast.api.forecast.WeatherServiceImpl
import com.isen.mehto.data.models.Location
import retrofit2.HttpException

class ForecastRepositoryImpl(private val weatherService: WeatherServiceImpl) : ForecastRepository {
    override suspend fun getTodayWeather(position: Position): Forecast {
        return try {
            mapWeatherFromWeatherResponse(weatherService.getTodayWeather(position.lat, position.lon))
        } catch (e: HttpException) {
            Forecast(
                "Unknown",
                Temperature(0.0, TemperatureUnit.CELSIUS),
                WeatherConditions.UNKNOWN,
                Percentage.fromValue(0.0)
            )
        }
    }

    override suspend fun getForecast(position: Position): List<Forecast> {
        return try {
            mapWeatherListFromForecastResponse(weatherService.getForecast(position.lat, position.lon))
        } catch (e: HttpException) {
            listOf()
        }
    }

    override suspend fun getLocationFromName(name: String): List<Location> {
        return try {
            weatherService.getLocationFromName(name)
        } catch (e: HttpException) {
            listOf()
        }
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
        return Forecast(singleDayForecastResponse.name, Temperature(singleDayForecastResponse.main.temp, TemperatureUnit.KELVIN), getWeatherTypeFromResponseWeatherType(singleDayForecastResponse.weather[0].main), Percentage.fromValue(singleDayForecastResponse.main.humidity.toDouble()))
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
