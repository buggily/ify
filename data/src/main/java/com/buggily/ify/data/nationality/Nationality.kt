package com.buggily.ify.data.nationality

import com.buggily.ify.data.rest.serializer.LocaleSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Locale
import kotlin.math.roundToInt

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

        val locale: Locale
            get() = Locale(String(), country)

        val percentage: Int
            get() {
                val percentage: Float = probability * 100
                return percentage.roundToInt()
            }

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
