package com.isen.mehto.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.viewmodels.ForecastViewModel
import org.koin.androidx.compose.get

@Composable
fun CarouselScreen() {
    val viewModel = viewModel<ForecastViewModel>(
        factory = ForecastViewModel.ViewModelFactory(get(), get(), get(), get())
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ForecastView(viewModel)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            ViewSelector(viewModel, "Default location", Icons.Filled.Home, 0)

            for (i in 1..viewModel.favorites.value.size){
                ViewSelector(viewModel, "Favorite location", Icons.Filled.Favorite, i)
            }
        }
    }
}

@Composable
private fun ViewSelector(viewModel: ForecastViewModel, label: String, icon: ImageVector, pos: Int) {
    val focusColor =
        if (viewModel.sliderPosition.intValue == pos)
            Color.White
        else
            Color.Black

    IconButton(onClick = { viewModel.sliderChangePosition(pos) }) {
        Icon(
            icon,
            contentDescription = label,
            tint = focusColor,
        )
    }
}
