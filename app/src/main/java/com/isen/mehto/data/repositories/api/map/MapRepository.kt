package com.isen.mehto.data.repositories.api.map

interface MapRepository {
    suspend fun getPrecipitationMap()
    suspend fun getTemperaturesMap()
    suspend fun getPressureMap()
    suspend fun getWindsMap()
    suspend fun getCloudMap()
}