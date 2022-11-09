package com.buggily.ify.feature.nationality

import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.model.nationality.Nationality

sealed class NationalityState {

    object Default : NationalityState()
    object Loading : NationalityState()

    data class Success(
        private val nationality: Nationality,
        private val format: Format,
    ) : NationalityState() {

        val nameText: String
            get() = nationality.name

        val countriesText: String?
            get() = nationality.countries.takeUnless { it.isEmpty() }?.joinToString {
                val countryText: String = it.locale.getDisplayCountry(format.locale)
                val percentageText: String = format.formatProbability(it.probability)
                it.run { "$countryText ($percentageText%)" }
            }
    }

    data class Error(
        private val error: String,
    ) : NationalityState() {

        val errorText: String
            get() = error
    }
}
