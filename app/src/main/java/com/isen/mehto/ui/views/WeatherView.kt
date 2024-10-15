package com.isen.mehto.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.ui.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen() {
    val viewModel: WeatherViewModel = viewModel()

    if (viewModel.weather.value == null) {
        Text(text = "Unable to show weather")
    }

    viewModel.weather.value?.let {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = it.weatherType.image),
                contentDescription = "Weather Icon",
            )
            Text(fontSize = 32.sp, text = "${it.temperature.inCelsius} °C")
            Text(fontSize = 24.sp, text = it.city)
        }
    }
}
