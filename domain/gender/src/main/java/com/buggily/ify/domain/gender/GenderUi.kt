package com.buggily.ify.domain.gender

data class GenderUi(
    val gender: Gender?,
    val nameText: String,
    val countText: String,
    val probabilityText: String,
) {

    sealed interface Gender {
        data object Male : Gender
        data object Female : Gender
    }
}
