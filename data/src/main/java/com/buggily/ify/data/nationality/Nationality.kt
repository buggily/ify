package com.buggily.ify.data.nationality

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nationality(
    @SerialName(NAME) val name: String,
    @SerialName(COUNTRIES) val countries: List<Country>,
) {

    @Serializable
    data class Country(
        @SerialName(COUNTRY) val country: String,
        @SerialName(PROBABILITY) val probability: Float,
    ) {

        private companion object {
            private const val COUNTRY = "country_id"
            private const val PROBABILITY = "probability"
        }
    }

    private companion object {
        private const val NAME = "name"
        private const val COUNTRIES = "country"
    }
}
