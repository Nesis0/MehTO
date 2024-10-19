package com.isen.mehto.viewmodels

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isen.mehto.data.repositories.api.ForecastRepository
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import com.isen.mehto.data.repositories.geolocation.GeolocationRepository

class GeolocationViewModel(private val geolocationRepository: GeolocationRepository){
    private val _geolocation = MutableLiveData<Location?>()
    val geolocation: LiveData<Location?> get() = _geolocation

    suspend fun fetchLastLocation() {
        geolocationRepository.getLastLocation {
            lastGeolocation -> _geolocation.value = lastGeolocation
        }
    }

    class ViewModelFactory(
        private val geolocationRepository: GeolocationRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                return GeolocationViewModel(
                    geolocationRepository = geolocationRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}