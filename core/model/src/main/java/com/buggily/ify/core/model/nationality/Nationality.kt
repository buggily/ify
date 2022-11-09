package com.buggily.ify.core.model.nationality

import java.util.Locale

data class Nationality(
    val name: String,
    val countries: List<Country>,
) {

    data class Country(
        val locale: Locale,
        val probability: Float,
    )

    data class Error(
        val error: String,
    )
}
