package com.buggily.ify.feature.nationality

import com.buggily.ify.core.domain.Format
import com.buggily.ify.data.nationality.Nationality

sealed interface NationalityUiState {

    data object Default : NationalityUiState
    data object Loading : NationalityUiState

    data class Response(
        val nationality: Nationality,
        val format: Format,
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
