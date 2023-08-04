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
data class RemoteNationality(

    @SerialName(NAME)
    val name: String,

    @SerialName(COUNTRIES)
    val countries: List<Country>,

    @SerialName(COUNT)
    val count: Int,
) {

    @Serializable
    data class Country(

        @SerialName(PROBABILITY)
        val probability: Float,

        @Serializable(LocaleSerializer::class)
        @SerialName(LOCALE)
        val locale: Locale,
    ) {

        private companion object {
            private const val PROBABILITY = "probability"
            private const val LOCALE = "country_id"
        }
    }

    @Serializable
    data class Error(
        @SerialName(MESSAGE)
        val message: String,
    ) {

        private companion object {
            private const val MESSAGE = "error"
        }
    }

    private companion object {
        private const val NAME = "name"
        private const val COUNTRIES = "country"
        private const val COUNT = "count"
    }

    private object LocaleSerializer : KSerializer<Locale> {

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
            serialName = "Locale",
            kind = PrimitiveKind.STRING,
        )

        override fun serialize(encoder: Encoder, value: Locale) {
            encoder.encodeString(value.country)
        }

        override fun deserialize(decoder: Decoder): Locale = Locale.Builder()
            .setRegion(decoder.decodeString())
            .build()
    }
}
