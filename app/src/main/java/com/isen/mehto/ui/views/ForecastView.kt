package com.isen.mehto.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.R
import com.isen.mehto.ui.theme.DashedDivider
import com.isen.mehto.ui.viewmodels.ForecastViewModel
import org.koin.androidx.compose.get

@Composable
fun ForecastScreen() {
    val viewModel = viewModel<ForecastViewModel>(
        factory = ForecastViewModel.ViewModelFactory(get(), get())
    )

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrentForecast(viewModel)
        Spacer(modifier = Modifier.height(30.dp))
        ForecastWeek(viewModel)
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun CurrentForecast(viewModel: ForecastViewModel) {
    if (viewModel.currentWeather.value == null) {
        Text(text = "Unable to show weather")
    }

    viewModel.currentWeather.value?.let {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = it.weatherType.image),
                contentDescription = "Weather Icon",
            )
            Text(fontSize = 32.sp, text = "${String.format("%.2f", it.temperature.toCelsius())} °C")
            Text(fontSize = 24.sp, text = it.city)
        }
    }
}

@Composable
private fun ForecastWeek(viewModel: ForecastViewModel) {
    Column {
        if (viewModel.weatherWeek.value.isNotEmpty()) DashedDivider()

        for (weather in viewModel.weatherWeek.value) {
            weather.let {
                Row(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.fillMaxHeight(0.8f),
                            painter = painterResource(id = it.weatherType.image),
                            contentDescription = "Weather Icon",
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(fontSize = 26.sp, text = "${it.temperature.toCelsius()} °C")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(fontSize = 26.sp, text = "${it.rainRisk}")
                        Image(
                            modifier = Modifier.fillMaxHeight(0.8f),
                            painter = painterResource(id = R.drawable.ic_rainrisk),
                            contentDescription = "Rain Risk Icon",
                        )
                    }
                }
                DashedDivider()
            }
        }
    }
}