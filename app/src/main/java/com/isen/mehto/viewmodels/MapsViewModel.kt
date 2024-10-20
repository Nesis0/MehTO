package com.isen.mehto.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MapsViewModel() : ViewModel() {
    var items = listOf("Precipitations", "Temperatures", "Winds", "Pressures", "Air Quality")
    val expanded = mutableStateOf(false)
    val selectedIndex = mutableIntStateOf(0)

    init {
        viewModelScope.launch {

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
