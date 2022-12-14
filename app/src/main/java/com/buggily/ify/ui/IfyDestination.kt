package com.buggily.ify.ui

sealed class IfyDestination {

    abstract val route: String

    object Home : IfyDestination() {

        override val route: String
            get() = "home"
    }
}
