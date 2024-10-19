package com.isen.mehto.data.geolocation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GeolocationService(private val context: Context, private val activity: Activity) {
    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
class GeolocationService(private val context: Context){

    fun getLastLocation(onSuccess: (Location?) -> Unit) {
        if (!checkForPermission())
            askForPermission()

        fusedLocationClient.lastLocation.addOnSuccessListener {
            location: Location? -> onSuccess(location)
        }
    }

    fun isGeolocationPermitted(): Boolean {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
    }

    private fun askForPermission(){
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }
}