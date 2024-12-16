package com.buggily.ify.data.nationality

import com.buggily.ify.local.nationality.LocalNationality
import com.buggily.ify.local.nationality.country.LocalNationalityCountry
import com.buggily.ify.remote.nationality.RemoteNationality

fun LocalNationality.WithCountries.to(): Nationality = Nationality(
    name = nationality.name,
    count = nationality.count,
    countries = countries.map { it.to() },
)

fun LocalNationalityCountry.to(): Nationality.Country = Nationality.Country(
    probability = probability,
    locale = locale,
)

fun RemoteNationality.toLocal(): LocalNationality = LocalNationality(
    name = name,
    count = count,
)

fun RemoteNationality.Country.toLocal(
    name: String,
): LocalNationalityCountry = LocalNationalityCountry(
    nationalityName = name,
    probability = probability,
    locale = locale,
)

fun RemoteNationality.toLocalWithCountries(): LocalNationality.WithCountries = LocalNationality.WithCountries(
    nationality = toLocal(),
    countries = countries.map { it.toLocal(name) },
)
