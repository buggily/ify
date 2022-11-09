package com.buggily.ify.feature.gender

import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.model.gender.Gender

sealed class GenderState {

    object Default : GenderState()
    object Loading : GenderState()

    data class Success(
        private val gender: Gender,
        private val format: Format,
    ) : GenderState() {

        val nameText: String
            get() = gender.name

        val genderText: String
            get() = format.formatLowercase(gender.gender.toString())

        val percentageText: String
            get() = format.formatProbability(gender.probability)

        val countText: String
            get() = format.formatNumber(gender.count)
    }

    data class Error(
        private val error: String,
    ) : GenderState() {

        val errorText: String
            get() = error
    }
}
