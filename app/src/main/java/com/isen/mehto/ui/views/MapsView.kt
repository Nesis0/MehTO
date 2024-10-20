package com.isen.mehto.ui.views

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.ui.theme.DashedDivider
import com.isen.mehto.viewmodels.MapsViewModel
import org.koin.androidx.compose.get

@Composable
fun MapsScreen() {
    val viewModel = viewModel<MapsViewModel>(
        factory = MapsViewModel.ViewModelFactory(get())
    )
    val maxWidthRatio = 0.9f

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(maxWidthRatio)
            .wrapContentSize(Alignment.TopStart),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = { viewModel.expanded.value = true })
                .fillMaxWidth(),
        ) {
            Text(
                viewModel.items[viewModel.selectedIndex.intValue],
                modifier = Modifier
                    .weight(1.0f)
                    .border(BorderStroke(1.dp, Color.Black))
                    .padding(10.dp),
            )

            if (viewModel.expanded.value) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = "DropDown Opened"
                )
            }
            else {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "DropDown Closed"
                )
            }
        }

        DropdownMenu(
            expanded = viewModel.expanded.value,
            onDismissRequest = { viewModel.expanded.value = false },
            modifier = Modifier
                .fillMaxWidth(maxWidthRatio)
                .background(Color.Gray.copy(alpha = 0.7f)),
        ) {
            viewModel.items.forEachIndexed { index, title ->
                DropdownMenuItem(
                    text = { Text(text = title) },
                    onClick = {
                        viewModel.selectedIndex.intValue = index
                        viewModel.expanded.value = false
                        viewModel.selectMapToShow()
                    }
                )
                if (index != viewModel.items.size - 1) DashedDivider()
            }
        }
        ImageLoader(viewModel.currentMap.value)
    }
}

@Composable
fun ImageLoader(bitmap: Bitmap?) {
    val imageModifier = Modifier.size(300f.dp)
    if (bitmap != null)
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
}