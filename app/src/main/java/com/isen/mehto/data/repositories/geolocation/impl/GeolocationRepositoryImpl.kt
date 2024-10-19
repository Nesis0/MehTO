package com.isen.mehto.data.repositories.geolocation.impl

import android.location.Location
import com.isen.mehto.data.geolocation.GeolocationService
import com.isen.mehto.data.repositories.geolocation.GeolocationRepository

class GeolocationRepositoryImpl(private val geolocationService: GeolocationService) : GeolocationRepository {
    override suspend fun getLastLocation(param: (Location?) -> Unit) {
        geolocationService.getLastLocation(param)
    }
    override suspend fun isGeolocationPermitted(): Boolean {
        return geolocationService.isGeolocationPermitted()
    }
}