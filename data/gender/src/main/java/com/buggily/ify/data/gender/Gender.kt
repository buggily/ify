package com.buggily.ify.data.gender

data class Gender(
    val name: String,
    val gender: Gender?,
    val probability: Float,
    val count: Int,
) {

    sealed interface Gender {
        data object Male : Gender
        data object Female : Gender
    }
}
