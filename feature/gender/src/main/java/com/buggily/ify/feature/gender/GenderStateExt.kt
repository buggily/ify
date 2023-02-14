package com.buggily.ify.feature.gender

val GenderState.Success.nameText: String
    get() = gender.name

val GenderState.Success.genderText: String?
    get() = gender.gender?.let { format.formatLowercase(it.toString()) }

val GenderState.Success.percentageText: String
    get() = format.formatProbability(gender.probability)

val GenderState.Success.countText: String
    get() = format.formatNumber(gender.count)

val GenderState.Error.errorText: String
    get() = error
