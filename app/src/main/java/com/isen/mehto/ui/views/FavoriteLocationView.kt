package com.isen.mehto.ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.ui.viewmodels.FavoriteLocationViewModel
import org.koin.androidx.compose.get

@Composable
fun FavoriteLocationScreen() {
    val viewModel = viewModel<FavoriteLocationViewModel>(
        factory = FavoriteLocationViewModel.ViewModelFactory(get())
    )
    Text(text = "Favorite Location Screen")
}