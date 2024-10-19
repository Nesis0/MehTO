package com.isen.mehto.data.geolocation

import android.app.Activity
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient

class GeolocationService(private val context: Context, private val activity: Activity) {
    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

    fun getLastLocation(onSuccess: (Location?) -> Unit) {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            location: Location? -> onSuccess(location)
        }
    }
}