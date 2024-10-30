package com.isen.mehto.data.forecast.api.forecast

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ForecastApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ForecastService = retrofit.create(ForecastService::class.java)
}