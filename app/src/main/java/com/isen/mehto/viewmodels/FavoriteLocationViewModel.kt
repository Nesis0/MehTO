package com.isen.mehto.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.models.Location
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.repositories.api.ForecastRepository
import kotlinx.coroutines.launch

class FavoriteLocationViewModel(
    private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
    private val forecastRepository: ForecastRepository,
) : ViewModel() {
    private val _userInput = mutableStateOf(String())
    val userInput = _userInput
    private val _position = mutableStateOf(Position(0.0f,0.0f))
    val position = _position
    private val _locations: MutableState<List<Location>> = mutableStateOf(listOf())
    val locations = _locations
    private val _isSearchActive = mutableStateOf(false)
    val isSearchActive = _isSearchActive
    private val _isAddFavoriteView = mutableStateOf(false)
    val isAddFavoriteView = _isAddFavoriteView
    private val _favoriteLocations: MutableState<List<FavoriteLocation>> = mutableStateOf(listOf())
    val favoriteLocations = _favoriteLocations

    fun onSearchTextChange(text: String) {
        _userInput.value = text
        viewModelScope.launch {
            _locations.value = forecastRepository.getLocationFromName(text)
        }
    }

    fun onValidate() {
        _isSearchActive.value = false

        if (_locations.value.isNotEmpty())
            saveLocation(_locations.value[0])
    }

    fun onSearchToggle() {
        _isSearchActive.value = !_isSearchActive.value
    }

    fun onSearchIconClick() {
        onSearchToggle()
    }

    fun saveLocation(location: Location) {
        viewModelScope.launch {
            val lastIndex = favoriteLocationRepository.getAllLocations().size + 1
            favoriteLocationRepository.insert(
                FavoriteLocation(
                    lastIndex,
                    lastIndex,
                    location.name,
                    location.lat.toFloat(),
                    location.lon.toFloat(),
                )
            )
        }

        _isAddFavoriteView.value = false
    }

    suspend fun loadFavoriteLocations() {
        if (!isAddFavoriteView.value)
            favoriteLocations.value = favoriteLocationRepository.getAllLocations().sortedBy {
                it.preference_index
            }
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
        private val forecastRepository: ForecastRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteLocationViewModel::class.java)) {
                return FavoriteLocationViewModel(
                    favoriteLocationRepository = favoriteLocationRepository,
                    forecastRepository = forecastRepository,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}