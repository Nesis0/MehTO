package com.isen.mehto.data.forecast.api.map

class MapServiceImpl: MapService {
    override suspend fun getPrecipitationMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ) {
        return MapApi.service.getPrecipitationMap()
    }

    override suspend fun getTemperaturesMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ) {
        return MapApi.service.getTemperaturesMap()
    }

    override suspend fun getPressureMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ) {
        return MapApi.service.getPressureMap()
    }

    override suspend fun getWindsMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ) {
        return MapApi.service.getWindsMap()
    }

    override suspend fun getCloudMap(
        zoomLevel: String?,
        xTiles: String?,
        yTiles: String?,
        apiKey: String?
    ) {
        return MapApi.service.getCloudMap()
    }

}