package com.isen.mehto.data.repositories.impl

import android.health.connect.datatypes.units.Percentage
import android.health.connect.datatypes.units.Temperature
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.models.WeatherType
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.weather.api.WeatherServiceImpl
import java.time.OffsetDateTime

class WeatherRepositoryImpl(private val weatherService: WeatherServiceImpl) : WeatherRepository {
    override suspend fun getTodayWeather(position: Position): Weather {
        return weatherService.getTodayWeather(position.latitude, position.longitude)
    }

    override suspend fun getForecast(position: Position): Weather {
        return weatherService.getForecast(position.latitude, position.longitude)
    }

    override suspend fun getCoordinatesFromCity(city: String): Position {
        return weatherService.getCoordinatesFromCity(city)
    }
}