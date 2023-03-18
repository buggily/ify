package com.buggily.ify.feature.nationality

import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.model.nationality.Nationality

sealed class NationalityUiState {

    object Default : NationalityUiState()
    object Loading : NationalityUiState()

    data class Success(
        val nationality: Nationality,
        val format: Format,
    ) : NationalityUiState()

    sealed class Error : NationalityUiState() {

        data class Api(
            val error: String,
        ) : Error()

        object Network : Error()
        object Else : Error()
    }
}
