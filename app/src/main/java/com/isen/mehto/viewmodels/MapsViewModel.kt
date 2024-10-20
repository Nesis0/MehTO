package com.isen.mehto.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isen.mehto.data.repositories.api.map.impl.MapRepositoryImpl
import kotlinx.coroutines.launch

class MapsViewModel(private val mapRepository: MapRepositoryImpl) : ViewModel() {
    var items = listOf("Precipitations", "Temperatures", "Winds", "Pressures", "Clouds")
    val expanded = mutableStateOf(false)
    val selectedIndex = mutableIntStateOf(0)
    private val _currentMap: MutableState<Bitmap?> =  mutableStateOf(null)
    val currentMap = _currentMap
    init {
        viewModelScope.launch {
            _currentMap.value = mapRepository.getPrecipitationMap()
        }
    }

    fun selectMapToShow(){
        viewModelScope.launch {
            currentMap.value = when (selectedIndex.intValue) {
                0 -> { mapRepository.getPrecipitationMap() }
                1 -> { mapRepository.getTemperaturesMap() }
                2 -> { mapRepository.getWindsMap() }
                3 -> { mapRepository.getPressureMap() }
                4 -> { mapRepository.getCloudMap() }
                else -> { currentMap.value }
            }
        }
    }

    class ViewModelFactory(
        private val mapRepository: MapRepositoryImpl
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
                return MapsViewModel(
                    mapRepository = mapRepository
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
