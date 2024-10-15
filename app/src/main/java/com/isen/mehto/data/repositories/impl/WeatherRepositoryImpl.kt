package com.isen.mehto.data.repositories.impl

import android.health.connect.datatypes.units.Percentage
import android.health.connect.datatypes.units.Temperature
import com.isen.mehto.data.models.Weather
import com.isen.mehto.data.models.WeatherType
import com.isen.mehto.data.repositories.WeatherRepository
import java.time.OffsetDateTime

class WeatherRepositoryImpl() : WeatherRepository {
    override suspend fun getWeather(): Weather {
        return getWeather(OffsetDateTime.now())
    }

    override suspend fun getWeather(time: OffsetDateTime): Weather {
        TODO("Not yet implemented")
    }
}