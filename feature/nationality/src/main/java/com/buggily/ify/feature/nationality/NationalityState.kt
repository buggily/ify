package com.buggily.ify.feature.nationality

import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.model.nationality.Nationality

sealed class NationalityState {

    object Default : NationalityState()
    object Loading : NationalityState()

    data class Success(
        val nationality: Nationality,
        val format: Format,
    ) : NationalityState()

    data class Error(
        val error: String,
    ) : NationalityState()
}
