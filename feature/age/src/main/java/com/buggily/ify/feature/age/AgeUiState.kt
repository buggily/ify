package com.buggily.ify.feature.age

import com.buggily.ify.core.domain.use.FormatNumber
import com.buggily.ify.core.model.age.Age

sealed class AgeUiState {

    object Default : AgeUiState()
    object Loading : AgeUiState()

    data class Success(
        val age: Age,
        val formatNumber: FormatNumber,
    ) : AgeUiState()

    sealed class Error : AgeUiState() {

        data class Api(
            val error: String,
        ) : Error()

        object Network : Error()
        object Else : Error()
    }
}
