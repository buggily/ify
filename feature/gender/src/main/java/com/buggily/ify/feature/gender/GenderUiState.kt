package com.buggily.ify.feature.gender

import com.buggily.ify.core.domain.Format
import com.buggily.ify.data.gender.Gender

sealed class GenderUiState {

    object Default : GenderUiState()
    object Loading : GenderUiState()

    data class Response(
        val gender: Gender,
        val format: Format,
    ) : GenderUiState()

    sealed class Failure : GenderUiState() {

        sealed class Remote : Failure() {

            data class Api(
                val message: String,
            ) : Remote()

            object Network : Remote()
        }

        object Else : Failure()
    }
}
