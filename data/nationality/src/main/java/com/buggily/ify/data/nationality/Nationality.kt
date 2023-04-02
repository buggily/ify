package com.buggily.ify.data.nationality

import java.util.Locale

data class Nationality(
    val name: String,
    val countries: List<Country>,
) {

    data class Country(
        val probability: Float,
        val locale: Locale,
    )
}
