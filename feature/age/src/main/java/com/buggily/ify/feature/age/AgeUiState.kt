package com.buggily.ify.feature.age

import com.buggily.ify.core.domain.FormatNumber
import com.buggily.ify.data.age.Age

sealed class AgeUiState {

    object Default : AgeUiState()
    object Loading : AgeUiState()

    data class Response(
        val age: Age,
        val formatNumber: FormatNumber,
    ) : AgeUiState()

    sealed class Failure : AgeUiState() {

        sealed class Remote : Failure() {

            data class Api(
                val message: String,
            ) : Remote()

            object Network : Remote()
        }

        object Else : Failure()
    }
}
