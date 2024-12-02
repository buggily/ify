package com.buggily.ify.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.buggily.ify.ui.theme.IfyTheme
import com.buggily.ify.ui.theme.darkColorSchemeCompat
import com.buggily.ify.ui.theme.lightColorSchemeCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IfyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            val isLight: Boolean = !isSystemInDarkTheme()
            val colorScheme: ColorScheme = remember(isLight) {
                if (isLight) {
                    lightColorSchemeCompat(this)
                } else {
                    darkColorSchemeCompat(this)
                }
            }

            IfyTheme(colorScheme) {
                IfyApp(Modifier.fillMaxSize())
            }
        }
    }
}
