package com.isen.mehto.ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.ui.viewmodels.SettingsViewModel
import org.koin.androidx.compose.get

@Composable
fun SettingsScreen() {
    val viewModel = viewModel<SettingsViewModel>(
        factory = SettingsViewModel.ViewModelFactory(get())
    )
    Text(text = "Settings Screen")
}