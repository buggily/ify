package com.buggily.ify.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        MainContent(Modifier.fillMaxSize())
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = IfyDestination.Home.route,
        modifier = modifier,
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
