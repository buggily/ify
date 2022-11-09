package com.buggily.ify.core.model.age

data class Age(
    val name: String,
    val age: Int?,
    val count: Int,
) {

    data class Error(
        val error: String,
    )
}
