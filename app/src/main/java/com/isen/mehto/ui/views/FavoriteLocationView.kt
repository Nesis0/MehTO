package com.isen.mehto.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.isen.mehto.data.models.Position
import com.isen.mehto.viewmodels.FavoriteLocationViewModel
import org.koin.androidx.compose.get

@Composable
fun FavoriteLocationScreen() {
    val viewModel = viewModel<FavoriteLocationViewModel>(
        factory = FavoriteLocationViewModel.ViewModelFactory(get())
    )
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(cameraPositionState.position.target) {
        viewModel.position.value = Position(
            cameraPositionState.position.target.latitude.toFloat(),
            cameraPositionState.position.target.longitude.toFloat(),
        )
    }

    Column {
        GoogleMap(cameraPositionState = cameraPositionState) {
            Marker(state = MarkerState(position = cameraPositionState.position.target))
        }
    }
}