package com.buggily.ify.ui.main

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.buggily.ify.ui.theme.IfyTheme
import com.buggily.ify.ui.home.HomeScreen

@Composable
fun MainScreen() {
    val context: Context = LocalContext.current
    val colorScheme: ColorScheme = if (isSystemInDarkTheme()) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }

    IfyTheme(colorScheme) {
        HomeScreen()
    }
}
