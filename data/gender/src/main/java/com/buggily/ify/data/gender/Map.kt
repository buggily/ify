package com.buggily.ify.data.gender

import com.buggily.ify.local.gender.LocalGender
import com.buggily.ify.remote.gender.RemoteGender

fun LocalGender.to(): Gender = Gender(
    name = name,
    gender = gender?.to(),
    probability = probability,
    count = count,
)

fun LocalGender.Gender.to(): Gender.Gender = when (this) {
    LocalGender.Gender.Male -> Gender.Gender.Male
    LocalGender.Gender.Female -> Gender.Gender.Female
}

fun RemoteGender.toLocal(): LocalGender = LocalGender(
    name = name,
    gender = gender?.toLocal(),
    probability = probability,
    count = count,
)

fun RemoteGender.Gender.toLocal(): LocalGender.Gender = when (this) {
    RemoteGender.Gender.Male -> LocalGender.Gender.Male
    RemoteGender.Gender.Female -> LocalGender.Gender.Female
}
