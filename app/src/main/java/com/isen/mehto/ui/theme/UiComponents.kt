package com.isen.mehto.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

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

@Composable
fun DoubleBorderContainer(content: @Composable() () -> Unit) {
    val blackBorder = Modifier
        .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
        .padding(5.dp)

    Box(modifier = blackBorder.fillMaxWidth()) {
        Column(modifier = blackBorder.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            content()
        }
    }
}