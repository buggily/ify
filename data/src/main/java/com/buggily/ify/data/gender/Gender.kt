package com.buggily.ify.data.gender

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data class Gender(
    @SerialName(NAME) val name: String,
    @SerialName(GENDER) val gender: Gender?,
    @SerialName(PROBABILITY) val probability: Float,
    @SerialName(COUNT) val count: Int,
) {

    val percentage: Int
        get() {
            val percentage: Float = probability * 100
            return percentage.roundToInt()
        }

    @Serializable
    enum class Gender {

        @SerialName(MALE)
        Male,

        @SerialName(FEMALE)
        Female;

        override fun toString(): String = when (this) {
            Male -> MALE
            Female -> FEMALE
        }

        private companion object {
            private const val MALE = "male"
            private const val FEMALE = "female"
        }
    }

    private companion object {
        private const val NAME = "name"
        private const val GENDER = "gender"
        private const val PROBABILITY = "probability"
        private const val COUNT = "count"
    }
}
