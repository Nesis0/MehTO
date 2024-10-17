package com.isen.mehto.data.repositories.impl

import android.health.connect.datatypes.units.Percentage
import android.health.connect.datatypes.units.Temperature
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.models.WeatherType
import com.isen.mehto.data.repositories.WeatherRepository
import com.isen.mehto.weather.api.WeatherServiceImpl
import java.time.OffsetDateTime

class WeatherRepositoryImpl() : WeatherRepository {
    override suspend fun getWeather(position: Position): Weather {
        return getWeather(position, OffsetDateTime.now())
class WeatherRepositoryImpl(private val weatherService: WeatherServiceImpl) : WeatherRepository {
    }

    override suspend fun getWeather(position: Position, time: OffsetDateTime): Weather {
        TODO("Not yet implemented")
    }

    override suspend fun getWeather(position: Position, start: OffsetDateTime, end: OffsetDateTime): List<Weather> {
        TODO("Not yet implemented")
    }
}