package com.isen.mehto.data.repositories.geolocation.impl

import com.isen.mehto.data.geolocation.GeolocationService
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.repositories.geolocation.GeolocationRepository

class GeolocationRepositoryImpl(private val geolocationService: GeolocationService) : GeolocationRepository {
    override suspend fun getLastPosition(): Position {
        val location = geolocationService.getLastLocation()
        return Position(location?.latitude ?: 0.0, location?.longitude ?: 0.0)
    }

    override suspend fun isGeolocationPermitted(): Boolean {
        return geolocationService.isGeolocationPermitted()
    }
}