package com.isen.mehto.data.repositories.geolocation

import android.location.Location

interface GeolocationRepository {
    suspend fun getLastLocation(param: (Location?) -> Unit)
    suspend fun isGeolocationPermitted(): Boolean
}