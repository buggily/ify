package com.buggily.ify.core.ui.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingIndicator(
    color: Color,
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        color = color,
        modifier = modifier,
    )
}
