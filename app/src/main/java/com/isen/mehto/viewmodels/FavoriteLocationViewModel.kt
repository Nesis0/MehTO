package com.isen.mehto.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.models.Position
import kotlinx.coroutines.launch

class FavoriteLocationViewModel(
    private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
) : ViewModel() {
    private val _userInput = mutableStateOf(String())
    val userInput = _userInput
    private val _position = mutableStateOf(Position(0.0f,0.0f))
    var position = _position

    init {
        viewModelScope.launch {

        }
    }

    suspend fun getAllLocations(): List<FavoriteLocation> {
        return favoriteLocationRepository.getAllLocations()
    }

    suspend fun insertLocation(location: FavoriteLocation) {
        favoriteLocationRepository.insert(location)
    }

    suspend fun getLocationInfo(displayName: String): FavoriteLocation? {
        return favoriteLocationRepository.read(displayName)
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
        private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteLocationViewModel::class.java)) {
                return FavoriteLocationViewModel(
                    favoriteLocationRepository = favoriteLocationRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}