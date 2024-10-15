package com.isen.mehto.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect

@Composable
fun DashedDivider() {
    Canvas(Modifier.fillMaxWidth()) {
        drawLine(
            color = Color.Black,
            strokeWidth = 5f,
            start = Offset(30f, 0f),
            end = Offset(size.width - 30f, 0f),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
        )
    }
}