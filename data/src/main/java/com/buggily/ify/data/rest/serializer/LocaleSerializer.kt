package com.buggily.ify.data.rest.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import java.util.Locale

object LocaleSerializer : KSerializer<Locale> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor(NAME) {
            indices.forEach {
                when (it) {
                    LANGUAGE_INDEX -> element<String>(LANGUAGE)
                    COUNTRY_INDEX -> element<String>(COUNTRY)
                }
            }
        }

    override fun serialize(
        encoder: Encoder,
        value: Locale,
    ) = encoder.encodeStructure(descriptor) {
        indices.forEach {
            when (it) {
                LANGUAGE_INDEX -> encodeStringElement(
                    descriptor = descriptor,
                    index = LANGUAGE_INDEX,
                    value = value.language,
                )
                COUNTRY_INDEX -> encodeStringElement(
                    descriptor = descriptor,
                    index = COUNTRY_INDEX,
                    value = value.country,
                )
            }
        }
    }

    override fun deserialize(
        decoder: Decoder,
    ): Locale = decoder.decodeStructure(descriptor) {
        var language = String()
        var country = String()

        indices.forEach {
            when (it) {
                LANGUAGE_INDEX -> language = decodeStringElement(
                    descriptor = descriptor,
                    index = it,
                )
                COUNTRY_INDEX -> country = decodeStringElement(
                    descriptor = descriptor,
                    index = it,
                )
            }
        }

        Locale(language, country)
    }

    private const val NAME = "Locale"

    private const val LANGUAGE = "language"
    private const val LANGUAGE_INDEX: Int = 0

    private const val COUNTRY = "country"
    private const val COUNTRY_INDEX: Int = LANGUAGE_INDEX + 1

    private val indices: IntRange
        get() = LANGUAGE_INDEX..COUNTRY_INDEX
}
