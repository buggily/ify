package com.buggily.ify.feature.gender

val GenderUiState.Response.nameText: String
    get() = gender.name

fun GenderUiState.Response.getGenderText(string: String): String = format.formatLowercase(string)

val GenderUiState.Response.percentageText: String
    get() = format.formatProbability(gender.probability)

val GenderUiState.Response.countText: String
    get() = format.formatNumber(gender.count)

val GenderUiState.Failure.Remote.Api.failureText: String
    get() = message
