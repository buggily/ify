package com.buggily.ify.feature.nationality

val NationalityUiState.Response.nameText: String
    get() = nationality.name

val NationalityUiState.Response.nationsText: String?
    get() = nationality.countries.takeUnless { it.isEmpty() }?.joinToString {
        val countryText: String = it.locale.getDisplayCountry(format.locale)
        val percentageText: String = format.formatProbability(it.probability)
        "$countryText ($percentageText%)"
    }

val NationalityUiState.Failure.Remote.Api.failureText: String
    get() = message
