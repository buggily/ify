package com.buggily.ify.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buggily.ify.ui.IfyDestination
import com.buggily.ify.ui.home.HomeScreen

@Composable
fun MainScreen() {
    NavHost(
        navController = rememberNavController(),
        startDestination = IfyDestination.Home.route,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable(
            route = IfyDestination.Home.route,
            arguments = emptyList(),
            deepLinks = emptyList(),
        ) {
            HomeScreen(
                viewModel = hiltViewModel(),
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
            )
        }
    }
}
