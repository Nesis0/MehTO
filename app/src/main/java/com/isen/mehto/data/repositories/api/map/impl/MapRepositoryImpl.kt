package com.isen.mehto.data.repositories.api.map.impl

import com.isen.mehto.data.forecast.api.map.MapService
import com.isen.mehto.data.repositories.api.map.MapRepository

class MapRepositoryImpl(private val mapService: MapService): MapRepository {
    override suspend fun getPrecipitationMap() {
        return mapService.getPrecipitationMap()
    }

    override suspend fun getTemperaturesMap() {
        return mapService.getTemperaturesMap()
    }

    override suspend fun getPressureMap() {
        return mapService.getPressureMap()
    }

    override suspend fun getWindsMap() {
        return mapService.getWindsMap()
    }

    override suspend fun getCloudMap() {
        return mapService.getCloudMap()
    }
}