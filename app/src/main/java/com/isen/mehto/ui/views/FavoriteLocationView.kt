package com.isen.mehto.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFavoriteLocationScreen() {
    val viewModel = viewModel<FavoriteLocationViewModel>(
        factory = FavoriteLocationViewModel.ViewModelFactory(get(), get())
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

        SearchBar(
            query = viewModel.userInput.value,
            onQueryChange = viewModel::onSearchTextChange,
            onSearch = { viewModel.onValidate() },
            active = viewModel.isSearchActive.value,
            onActiveChange = { viewModel.onSearchToggle() },
            placeholder = { Text("Search location") },
            trailingIcon = {
                TextButton(
                    onClick = { viewModel.onSearchIconClick() },
                    modifier = Modifier.background(Color.Transparent)
                ) {
                    if (viewModel.isSearchActive.value)
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close Icon"
                        )
                    else
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Icon"
                        )
                }
            },
            shape = SearchBarDefaults.fullScreenShape,
            modifier = Modifier.fillMaxWidth(0.9f),
        ) {
            Column {
                for (location in viewModel.locations.value) {
                    TextButton(
                        onClick = { viewModel.saveLocation(location) },
                        content = { Text("${location.name} (${location.country})") },
                        shape = RectangleShape,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        GoogleMap(cameraPositionState = cameraPositionState) {
            Marker(state = MarkerState(position = cameraPositionState.position.target))
        }
    }
}