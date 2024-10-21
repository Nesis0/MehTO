package com.isen.mehto.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Slider(
            modifier = Modifier.fillMaxWidth(0.6f),
            value = viewModel.sliderPosition.floatValue,
            onValueChange = { newValue ->
                viewModel.sliderChangePosition(newValue)
            },
            valueRange = 0f..viewModel.favorites.value.size.toFloat(),
        )
    }
}
