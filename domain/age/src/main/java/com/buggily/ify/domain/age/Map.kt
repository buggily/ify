package com.buggily.ify.domain.age

import com.buggily.ify.core.domain.FormatNumber
import com.buggily.ify.data.age.Age

fun Age.toUi(
    formatNumber: FormatNumber,
): AgeUi {
    val nameText: String = name
    val ageText: String? = age?.toString()
    val countText: String? = formatNumber(count)

    return AgeUi(
        nameText = nameText,
        ageText = ageText,
        countText = countText,
    )
}
