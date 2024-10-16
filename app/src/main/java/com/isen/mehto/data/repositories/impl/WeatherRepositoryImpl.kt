package com.isen.mehto.data.repositories.impl

import android.health.connect.datatypes.units.Percentage
import android.health.connect.datatypes.units.Temperature
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.models.WeatherType
import com.isen.mehto.data.repositories.WeatherRepository
import java.time.OffsetDateTime

class WeatherRepositoryImpl() : WeatherRepository {
    override suspend fun getWeather(position: Position): Weather {
        return getWeather(position, OffsetDateTime.now())
    }

    override suspend fun getWeather(position: Position, time: OffsetDateTime): Weather {
        TODO("Not yet implemented")
    }

    override suspend fun getWeather(position: Position, start: OffsetDateTime, end: OffsetDateTime): List<Weather> {
        TODO("Not yet implemented")
    }
}