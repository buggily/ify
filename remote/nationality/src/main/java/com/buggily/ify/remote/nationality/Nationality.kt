package com.buggily.ify.remote.nationality

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.Locale

@Serializable
data class Nationality(
    @SerialName(NAME) val name: String,
    @SerialName(COUNTRIES) val countries: List<Country>,
) {

    @Serializable
    data class Country(
        @Serializable(LocaleSerializer::class)
        @SerialName(LOCALE) val locale: Locale,
        @SerialName(PROBABILITY) val probability: Float,
    ) {

        private companion object {
            private const val LOCALE = "country_id"
            private const val PROBABILITY = "probability"
        }
    }

    @Serializable
    data class Error(
        @SerialName(ERROR) val error: String,
    ) {

        private companion object {
            private const val ERROR = "error"
        }
    }

    private companion object {
        private const val NAME = "name"
        private const val COUNTRIES = "country"
    }

    private object LocaleSerializer : KSerializer<Locale> {

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
            serialName = "Locale",
            kind = PrimitiveKind.STRING,
        )

        override fun serialize(encoder: Encoder, value: Locale) {
            val string: String = value.country
            encoder.encodeString(string)
        }

        override fun deserialize(decoder: Decoder): Locale {
            val string: String = decoder.decodeString()
            return Locale(String(), string)
        }
    }
}
