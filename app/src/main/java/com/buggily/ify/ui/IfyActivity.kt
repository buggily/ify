package com.buggily.ify.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.buggily.ify.ui.theme.IfyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IfyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            IfyTheme {
                IfyApp(Modifier.fillMaxSize())
            }
        }
    }
}
