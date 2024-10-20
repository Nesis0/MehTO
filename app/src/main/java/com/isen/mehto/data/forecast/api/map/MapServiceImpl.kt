package com.isen.mehto.data.forecast.api.map

import okhttp3.ResponseBody

class MapServiceImpl: MapService {
    override suspend fun getPrecipitationMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ): ResponseBody {
        return MapApi.service.getPrecipitationMap()
    }

    override suspend fun getTemperaturesMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ): ResponseBody {
        return MapApi.service.getTemperaturesMap()
    }

    override suspend fun getPressureMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ): ResponseBody {
        return MapApi.service.getPressureMap()
    }

    override suspend fun getWindsMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ): ResponseBody {
        return MapApi.service.getWindsMap()
    }

    override suspend fun getCloudMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ): ResponseBody {
        return MapApi.service.getCloudMap()
    }

}