package com.buggily.ify.feature.nationality

val NationalityUiState.Success.nameText: String
    get() = nationality.name

val NationalityUiState.Success.nationsText: String?
    get() = nationality.countries.takeUnless { it.isEmpty() }?.joinToString {
        val countryText: String = it.locale.getDisplayCountry(format.locale)
        val percentageText: String = format.formatProbability(it.probability)
        "$countryText ($percentageText%)"
    }

val NationalityUiState.Error.Api.errorText: String
    get() = error
