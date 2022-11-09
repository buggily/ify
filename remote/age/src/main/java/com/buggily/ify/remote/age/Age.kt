package com.buggily.ify.remote.age

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Age(
    @SerialName(NAME) val name: String,
    @SerialName(AGE) val age: Int?,
    @SerialName(COUNT) val count: Int,
) {

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
        private const val AGE = "age"
        private const val COUNT = "count"
    }
}
