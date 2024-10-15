package com.isen.mehto.data.repositories.impl

import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.repositories.WeatherRepository
import java.time.LocalDateTime

class WeatherRepositoryImpl() : WeatherRepository {
    override suspend fun getWeather(): Weather {
        TODO("Not yet implemented")
    }

    override suspend fun getWeather(time: LocalDateTime): Weather {
        TODO("Not yet implemented")
    }
}