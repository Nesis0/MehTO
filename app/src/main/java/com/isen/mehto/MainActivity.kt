package com.isen.mehto

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.isen.mehto.ui.theme.MehTOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MehTOTheme {
                Navigation()
            }
        }
        val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (!checkForPermission(Manifest.permission.ACCESS_FINE_LOCATION))
            askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, 1)

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                //TODO(Do something with location)
            }
        }
    }

    private fun checkForPermission(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
    }

    private fun askForPermission(permission: String, reqCode: Int){
        ActivityCompat.requestPermissions(this,
            arrayOf(permission), reqCode)
    }
}