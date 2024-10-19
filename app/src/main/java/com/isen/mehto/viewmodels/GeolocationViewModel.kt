package com.isen.mehto.viewmodels

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.isen.mehto.data.repositories.geolocation.GeolocationRepository

class GeolocationViewModel(private val geolocationRepository: GeolocationRepository){
    private val _geolocation = MutableLiveData<Location?>()
    val geolocation: LiveData<Location?> get() = _geolocation

    suspend fun fetchLastLocation() {
        geolocationRepository.getLastLocation {
            lastGeolocation -> _geolocation.value = lastGeolocation
        }
    }
}