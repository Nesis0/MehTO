package com.isen.mehto.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isen.mehto.data.repositories.db.impl.OfflineConfigRepository
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import com.isen.mehto.data.entity.FavoriteLocation

class FavoriteLocationViewModel(private val favoriteLocationRepository: OfflineFavoriteLocationRepository) : ViewModel() {
    suspend fun getAllLocations(): List<FavoriteLocation> {
        return favoriteLocationRepository.getAllLocations()
    }

    suspend fun insertLocation(location: FavoriteLocation) {
        favoriteLocationRepository.insert(location)
    }

    suspend fun getLocationInfo(displayName: String): FavoriteLocation? {
        return favoriteLocationRepository.readByLocationName(displayName)
    }

    suspend fun updateLocation(location: FavoriteLocation) {
        favoriteLocationRepository.update(location)
    }

    suspend fun deleteLocation(location: FavoriteLocation) {
        favoriteLocationRepository.delete(location)
    }

    suspend fun clearLocationList() {
        favoriteLocationRepository.clearLocationList()
    }

    class ViewModelFactory(
        private val configRepository: OfflineConfigRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                return SettingsViewModel(
                    configRepository = configRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}