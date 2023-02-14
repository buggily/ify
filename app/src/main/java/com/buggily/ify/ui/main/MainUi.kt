package com.buggily.ify.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buggily.ify.ui.IfyDestination
import com.buggily.ify.ui.home.HomeScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = IfyDestination.startDestination.route,
        modifier = modifier,
    ) {
        composable(IfyDestination.Home.route) {
            HomeScreen(
                viewModel = hiltViewModel(),
                modifier = Modifier
                    .fillMaxSize()
                    .safeContentPadding(),
            )
        }
    }
}
