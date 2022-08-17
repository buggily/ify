package com.buggily.ify.ui

sealed class IfyDestination {

    abstract val name: String

    object Default : IfyDestination() {

        override val name: String
            get() = "default"
    }
}
