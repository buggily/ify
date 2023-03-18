package com.buggily.ify.feature.gender

import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.model.gender.Gender

sealed class GenderUiState {

    object Default : GenderUiState()
    object Loading : GenderUiState()

    data class Success(
        val gender: Gender,
        val format: Format,
    ) : GenderUiState()

    sealed class Error : GenderUiState() {

        data class Api(
            val error: String,
        ) : Error()

        object Network : Error()
        object Else : Error()
    }
}
