package com.buggily.ify.ui.main

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buggily.ify.ui.IfyDestination
import com.buggily.ify.ui.home.HomeScreen

@OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class,
)

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier) {
        NavHost(
            navController = rememberNavController(),
            startDestination = IfyDestination.startDestination.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumedWindowInsets(it),
        ) {
            composable(IfyDestination.Home.route) {
                HomeScreen(
                    viewModel = hiltViewModel(),
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                )
            }
        }
    }
}
