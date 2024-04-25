package com.buggily.ify.feature.age

import com.buggily.ify.domain.age.AgeUi

sealed interface AgeUiState {

    data object Default : AgeUiState
    data object Loading : AgeUiState

    data class Response(
        val age: AgeUi,
    ) : AgeUiState

    sealed interface Failure : AgeUiState {

        sealed interface Remote : Failure {

            data class Api(
                val message: String,
            ) : Remote

            data object Network : Remote
        }

        data object Else : Failure
    }
}
