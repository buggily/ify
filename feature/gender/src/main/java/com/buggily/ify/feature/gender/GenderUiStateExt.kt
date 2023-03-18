package com.buggily.ify.feature.gender

val GenderUiState.Success.nameText: String
    get() = gender.name

val GenderUiState.Success.genderText: String?
    get() = gender.gender?.let { format.formatLowercase(it.toString()) }

val GenderUiState.Success.percentageText: String
    get() = format.formatProbability(gender.probability)

val GenderUiState.Success.countText: String
    get() = format.formatNumber(gender.count)

val GenderUiState.Error.Api.errorText: String
    get() = error
