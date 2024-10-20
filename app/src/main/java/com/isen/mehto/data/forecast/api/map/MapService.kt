package com.isen.mehto.data.forecast.api.map

import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY: String = "63b335fbcbab900e14a56296d96716b4"

interface MapService {
    suspend fun getPrecipitationMap()

    suspend fun getTemperaturesMap()

    suspend fun getPressureMap()

    suspend fun getWindsMap()

    suspend fun getCloudMap()
}