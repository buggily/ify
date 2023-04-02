package com.buggily.ify.feature.nationality

import com.buggily.ify.core.domain.Format
import com.buggily.ify.data.nationality.Nationality

sealed class NationalityUiState {

    object Default : NationalityUiState()
    object Loading : NationalityUiState()

    data class Response(
        val nationality: Nationality,
        val format: Format,
    ) : NationalityUiState()

    sealed class Failure : NationalityUiState() {

        sealed class Remote : Failure() {

            data class Api(
                val message: String,
            ) : Remote()

            object Network : Remote()
        }

        object Else : Failure()
    }
}
