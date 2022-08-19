package com.buggily.ify.ui.ext

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedBox(
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val boxModifier: Modifier = modifier.border(
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(
            width = 2.dp,
            color = color,
        ),
    )

    Box(boxModifier) {
        content()
    }
}
