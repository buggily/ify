package com.buggily.ify.data.age

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgeError(
    @SerialName(ERROR) val error: String,
) {

    private companion object {
        private const val ERROR = "error"
    }
}
