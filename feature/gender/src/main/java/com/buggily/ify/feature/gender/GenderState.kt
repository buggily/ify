package com.buggily.ify.feature.gender

import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.model.gender.Gender

sealed class GenderState {

    object Default : GenderState()
    object Loading : GenderState()

    data class Success(
        val gender: Gender,
        val format: Format,
    ) : GenderState()

    data class Error(
        val error: String,
    ) : GenderState()
}
