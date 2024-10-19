package com.isen.mehto.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    AddFavoriteLocationScreen()
}

@Composable
fun AddFavoriteLocationScreen() {
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

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = viewModel.userInput.value,
            onValueChange = { viewModel.userInput.value = it },
            label = { Text("Search location") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.Gray.copy(alpha = 0.7f),
                focusedContainerColor = Color.Gray.copy(alpha = 0.7f),
                unfocusedBorderColor = Color.Black,
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                )
            },
        )

        Spacer(Modifier.height(20.dp))

        GoogleMap(cameraPositionState = cameraPositionState) {
            Marker(state = MarkerState(position = cameraPositionState.position.target))
        }
    }
}