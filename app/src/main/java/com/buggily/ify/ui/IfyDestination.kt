package com.buggily.ify.ui

sealed interface IfyDestination {

    val route: String

    data object Home : IfyDestination {
        override val route: String = "home"
    }

    companion object {
        val startDestination: IfyDestination = Home
    }
}
