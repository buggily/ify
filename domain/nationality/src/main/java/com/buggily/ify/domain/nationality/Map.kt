package com.buggily.ify.domain.nationality

import com.buggily.ify.core.domain.Format
import com.buggily.ify.data.nationality.Nationality

fun Nationality.toUi(
    format: Format,
): NationalityUi {
    val nameText: String = name
    val countText: String = format.formatNumber(count)

    val nationsText: String? = countries.takeUnless {
        it.isEmpty()
    }?.sortedByDescending {
        it.probability
    }?.joinToString {
        val countryText: String = it.locale.getDisplayCountry(format.locale)
        val percentageText: String = format.formatProbability(it.probability)
        "$countryText ($percentageText%)"
    }

    return NationalityUi(
        nameText = nameText,
        countText = countText,
        nationsText = nationsText,
    )
}