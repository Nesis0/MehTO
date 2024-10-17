package com.isen.mehto.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isen.mehto.data.OfflineConfigRepository
import com.isen.mehto.data.OfflineFavoriteLocationRepository
import com.isen.mehto.data.entity.FavoriteLocation

class FavoriteLocationViewModel(private val favoriteLocationRepository: OfflineFavoriteLocationRepository) : ViewModel() {
    suspend fun getAllCities(): List<FavoriteLocation> {
        return favoriteLocationRepository.getAllCities()
    }

    suspend fun insertLocation(location: FavoriteLocation) {
        favoriteLocationRepository.insertCity(location)
    }

    suspend fun getLocationInfo(displayName: String): FavoriteLocation? {
        return favoriteLocationRepository.getLocationInfo(displayName)
    }

    suspend fun updateCity(location: FavoriteLocation) {
        favoriteLocationRepository.updateCity(location)
    }

    suspend fun deleteCity(location: FavoriteLocation) {
        favoriteLocationRepository.deleteCity(location)
    }

    suspend fun clearCityList() {
        favoriteLocationRepository.clearCityList()
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