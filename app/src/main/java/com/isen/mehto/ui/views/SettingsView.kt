package com.isen.mehto.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.viewmodels.SettingsViewModel
import org.koin.androidx.compose.get

@Composable
fun SettingsScreen() {
    val viewModel = viewModel<SettingsViewModel>(
        factory = SettingsViewModel.ViewModelFactory(get())
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.95f)
    ) {
        Text(fontSize = 24.sp, text = "Settings")

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .fillMaxWidth()
        ) {
            viewModel.settings.value.forEachIndexed { index, config ->
                Row(modifier = Modifier.padding(10.dp)) {
                    Text(text = config.name)
                }

                if (index < viewModel.settings.value.size - 1)
                    HorizontalDivider(color = Color.Black, thickness = 2.dp)
            }
        }
    }
}