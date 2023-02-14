package com.buggily.ify.feature.age

import com.buggily.ify.core.domain.use.FormatNumber
import com.buggily.ify.core.model.age.Age

sealed class AgeState {

    object Default : AgeState()
    object Loading : AgeState()

    data class Success(
        val age: Age,
        val formatNumber: FormatNumber,
    ) : AgeState()

    data class Error(
        val error: String,
    ) : AgeState()
}
