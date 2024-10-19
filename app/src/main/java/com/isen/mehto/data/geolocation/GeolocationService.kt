package com.isen.mehto.data.geolocation

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient

class GeolocationService(private val fusedLocationProviderClient: FusedLocationProviderClient) {
    fun getLastLocation(onSuccess: (Location?) -> Unit) {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            location: Location? -> onSuccess(location)
        }
    }
}