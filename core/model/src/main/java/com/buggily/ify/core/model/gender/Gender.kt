package com.buggily.ify.core.model.gender

data class Gender(
    val name: String,
    val gender: Gender?,
    val probability: Float,
    val count: Int,
) {

    enum class Gender {
        Male,
        Female;
    }

    data class Error(
        val error: String,
    )
}
