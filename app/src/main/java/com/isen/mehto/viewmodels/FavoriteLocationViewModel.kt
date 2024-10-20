package com.isen.mehto.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.repositories.db.impl.OfflineFavoriteLocationRepository
import com.isen.mehto.data.entity.FavoriteLocation
import com.isen.mehto.data.models.Forecast
import com.isen.mehto.data.models.Location
import com.isen.mehto.data.models.Position
import com.isen.mehto.data.repositories.api.ForecastRepository
import kotlinx.coroutines.launch

data class LocationItem(
    val location: FavoriteLocation,
    val isSelected: MutableState<Boolean>,
    val forecast: Forecast,
)

class FavoriteLocationViewModel(
    private val favoriteLocationRepository: OfflineFavoriteLocationRepository,
    private val forecastRepository: ForecastRepository,
) : ViewModel() {
    private val _userInput = mutableStateOf(String())
    val userInput = _userInput
    private val _position = mutableStateOf(Position(0.0f,0.0f))
    val position = _position
    private val _locations = mutableStateOf(listOf<Location>())
    val locations = _locations
    private val _isSearchActive = mutableStateOf(false)
    val isSearchActive = _isSearchActive
    private val _isAddFavoriteView = mutableStateOf(false)
    val isAddFavoriteView = _isAddFavoriteView
    private val _favoriteLocations = mutableStateOf(listOf<LocationItem>())
    val favoriteLocations = _favoriteLocations

    fun selectAllItems() {
        val checked = isAllLocationsSelected()

        _favoriteLocations.value = _favoriteLocations.value.map {
            it.isSelected.value = !checked
            it
        }
    }

    fun toggleSelf(checked: Boolean, index: Int) {
        _favoriteLocations.value[index].isSelected.value = checked
    }

    fun isAllLocationsSelected(): Boolean {
        if (_favoriteLocations.value.isEmpty()) return false
        return _favoriteLocations.value.all { it.isSelected.value }
    }

    fun deleteSelectedLocations() {
        viewModelScope.launch {
            _favoriteLocations.value
                .filter { it.isSelected.value }
                .forEach { deleteLocation(it.location) }

            loadFavoriteLocations()
        }
    }

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
            _favoriteLocations.value = favoriteLocationRepository.getAllLocations()
                .sortedBy { it.preference_index }
                .map {
                    val pos = Position(it.longitude, it.latitude)
                    LocationItem(it, mutableStateOf(false), forecastRepository.getTodayWeather(pos))
                }
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