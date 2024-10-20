package com.isen.mehto.data.forecast.api.map

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MapApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://tile.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MapService = retrofit.create(MapService::class.java)
}