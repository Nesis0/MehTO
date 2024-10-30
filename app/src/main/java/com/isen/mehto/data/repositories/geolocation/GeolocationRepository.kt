package com.isen.mehto.data.repositories.geolocation

import com.isen.mehto.data.models.Position

interface GeolocationRepository {
    suspend fun getLastPosition(): Position
    suspend fun isGeolocationPermitted(): Boolean
}