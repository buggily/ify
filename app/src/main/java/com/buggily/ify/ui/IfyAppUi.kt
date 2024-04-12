package com.buggily.ify.ui

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
import com.buggily.ify.core.ui.ext.ZERO

@Composable
fun IfyApp(modifier: Modifier = Modifier) {
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
                route = IfyDestination.Ify.route,
                arguments = emptyList(),
                deepLinks = emptyList(),
            ) {
                IfyScreen(
                    viewModel = hiltViewModel(),
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
