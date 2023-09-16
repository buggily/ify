package com.buggily.ify.ui.main

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buggily.ify.ui.IfyDestination
import com.buggily.ify.ui.home.HomeScreen
import com.buggily.ify.ui.theme.ZERO

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.ZERO,
        modifier = modifier,
    ) {
        NavHost(
            navController = rememberNavController(),
            startDestination = IfyDestination.startDestination.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumeWindowInsets(it),
        ) {
            composable(
                route = IfyDestination.Home.route,
                arguments = emptyList(),
                deepLinks = emptyList(),
            ) {
                HomeScreen(
                    viewModel = hiltViewModel(),
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
