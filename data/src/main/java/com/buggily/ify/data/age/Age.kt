package com.buggily.ify.data.age

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Age(
    @SerialName(NAME) val name: String,
    @SerialName(AGE) val age: Int?,
    @SerialName(COUNT) val count: Int,
) {

    private companion object {
        private const val NAME = "name"
        private const val AGE = "age"
        private const val COUNT = "count"
    }
}
