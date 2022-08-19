package com.buggily.ify.data.gender

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenderError(
    @SerialName(ERROR) val error: String,
) {

    private companion object {
        private const val ERROR = "error"
    }
}
