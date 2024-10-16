package com.isen.mehto.data.repositories.impl

import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.repositories.WeatherRepository
import java.time.OffsetDateTime

class WeatherRepositoryImpl() : WeatherRepository {
    override suspend fun getWeather(): Weather {
        return getWeather(OffsetDateTime.now())
    }

    override suspend fun getWeather(time: OffsetDateTime): Weather {
        TODO("Not yet implemented")
    }

    override suspend fun getWeather(start: OffsetDateTime, end: OffsetDateTime): List<Weather> {
        TODO("Not yet implemented")
    }
}