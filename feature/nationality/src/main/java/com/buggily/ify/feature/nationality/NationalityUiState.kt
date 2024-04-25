package com.buggily.ify.feature.nationality

import com.buggily.ify.domain.nationality.NationalityUi

sealed interface NationalityUiState {

    data object Default : NationalityUiState
    data object Loading : NationalityUiState

    data class Response(
        val nationality: NationalityUi,
    ) : NationalityUiState

    sealed interface Failure : NationalityUiState {

        sealed interface Remote : Failure {

            data class Api(
                val message: String,
            ) : Remote

            data object Network : Remote
        }

        data object Else : Failure
    }
}
