package com.buggily.ify.domain.gender

import com.buggily.ify.core.domain.Format
import com.buggily.ify.data.gender.Gender

fun Gender.toUi(
    format: Format,
): GenderUi {
    val gender: GenderUi.Gender? = when (gender) {
        is Gender.Gender.Male -> GenderUi.Gender.Male
        is Gender.Gender.Female -> GenderUi.Gender.Female
        else -> null
    }

    val nameText: String = name
    val countText: String = format.formatNumber(count)
    val probabilityText: String = format.formatProbability(probability)

    return GenderUi(
        gender = gender,
        nameText = nameText,
        countText = countText,
        probabilityText = probabilityText,
    )
}