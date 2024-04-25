package com.buggily.ify.feature.gender

import com.buggily.ify.domain.gender.GenderUi

sealed interface GenderUiState {

    data object Default : GenderUiState
    data object Loading : GenderUiState

    data class Response(
        val gender: GenderUi,
    ) : GenderUiState

    sealed interface Failure : GenderUiState {

        sealed interface Remote : Failure {

            data class Api(
                val message: String,
            ) : Remote

            data object Network : Remote
        }

        data object Else : Failure
    }
}
