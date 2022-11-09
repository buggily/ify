package com.buggily.ify.feature.age

import com.buggily.ify.core.domain.use.FormatNumber
import com.buggily.ify.core.model.age.Age

sealed class AgeState {

    object Default : AgeState()
    object Loading : AgeState()

    data class Success(
        private val age: Age,
        private val formatNumber: FormatNumber,
    ) : AgeState() {

        val nameText: String
            get() = age.name

        val ageText: String
            get() = age.age.toString()

        val countText: String
            get() = formatNumber(age.count)
    }

    data class Error(
        private val error: String,
    ) : AgeState() {

        val errorText: String
            get() = error
    }
}
