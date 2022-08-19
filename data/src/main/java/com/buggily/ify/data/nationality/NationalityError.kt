package com.buggily.ify.data.nationality

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NationalityError(
    @SerialName(ERROR) val error: String,
) {

    private companion object {
        private const val ERROR = "error"
    }
}
