package com.buggily.ify.ui

sealed interface IfyDestination {

    val route: String

    data object Ify : IfyDestination {
        override val route: String = "ify"
    }

    companion object {
        val startDestination: IfyDestination = Ify
    }
}
