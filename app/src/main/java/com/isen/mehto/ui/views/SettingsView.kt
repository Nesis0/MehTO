package com.isen.mehto.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.data.entity.ConfigType
import com.isen.mehto.data.models.TemperatureUnit
import com.isen.mehto.viewmodels.ConfigItem
import com.isen.mehto.viewmodels.SettingsViewModel
import org.koin.androidx.compose.get

@Composable
fun SettingsScreen() {
    val viewModel = viewModel<SettingsViewModel>(
        factory = SettingsViewModel.ViewModelFactory(get())
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(0.95f)
    ) {
        Text(fontSize = 24.sp, text = "Settings")

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .fillMaxWidth()
        ) {
            viewModel.settings.value.forEachIndexed { index, configItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(configItem.config.value.type.displayName)

                    ConfigValueSelector(configItem)
                }

                if (index < viewModel.settings.value.size - 1)
                    HorizontalDivider(color = Color.Black, thickness = 2.dp)
            }
        }
    }
}

@Composable
fun ConfigValueSelector(configItem: ConfigItem) {
    Box {
        Row(modifier = Modifier
            .clickable { configItem.isExpanded.value = true }
            .align(Alignment.CenterEnd)
        ) {
            Text(text = configItem.config.value.value)

            if (configItem.isExpanded.value) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = "DropDown Opened"
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "DropDown Closed"
                )
            }
        }
        DropdownMenu(
            expanded = configItem.isExpanded.value,
            onDismissRequest = { configItem.isExpanded.value = false },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            when (configItem.config.value.type) {
                ConfigType.UNIT -> TemperatureUnit.entries.forEach {
                    Option(it.toString(), configItem)
                }

                ConfigType.ENABLE_GEOLOCATION -> {
                    Option("true", configItem)
                    Option("false", configItem)
                }
            }
        }
    }
}

@Composable
private fun Option(value: String, configItem: ConfigItem) {
    val viewModel = viewModel<SettingsViewModel>(
        factory = SettingsViewModel.ViewModelFactory(get())
    )

    DropdownMenuItem(
        text = { Text(value) },
        onClick = { viewModel.saveSetting(value, configItem) }
    )
}
