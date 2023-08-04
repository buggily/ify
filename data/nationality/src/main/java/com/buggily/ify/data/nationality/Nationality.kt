package com.buggily.ify.data.nationality

import java.util.Locale

data class Nationality(
    val name: String,
    val countries: List<Country>,
    val count: Int,
) {

    data class Country(
        val probability: Float,
        val locale: Locale,
    )
}
