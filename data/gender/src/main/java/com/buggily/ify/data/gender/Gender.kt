package com.buggily.ify.data.gender

data class Gender(
    val name: String,
    val gender: Gender?,
    val probability: Float,
    val count: Int,
) {

    sealed class Gender {
        object Male : Gender()
        object Female : Gender()
    }
}
