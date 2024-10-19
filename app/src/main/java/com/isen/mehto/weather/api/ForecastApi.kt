package com.isen.mehto.weather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ForecastApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: WeatherService = retrofit.create(WeatherService::class.java)
}