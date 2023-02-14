package com.buggily.ify.feature.nationality

val NationalityState.Success.nameText: String
    get() = nationality.name

val NationalityState.Success.nationsText: String?
    get() = nationality.countries.takeUnless { it.isEmpty() }?.joinToString {
        val countryText: String = it.locale.getDisplayCountry(format.locale)
        val percentageText: String = format.formatProbability(it.probability)
        "$countryText ($percentageText%)"
    }

val NationalityState.Error.errorText: String
    get() = error
