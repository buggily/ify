package com.buggily.ify.data.nationality

import com.buggily.ify.local.nationality.LocalNationality
import com.buggily.ify.local.nationality.country.LocalNationalityCountry
import com.buggily.ify.remote.nationality.RemoteNationality

fun LocalNationality.WithCountries.to(): Nationality = Nationality(
    name = nationality.name,
    countries = countries.map { it.to() },
)

fun LocalNationalityCountry.to(): Nationality.Country = Nationality.Country(
    probability = probability,
    locale = locale,
)

fun RemoteNationality.toLocal(): LocalNationality = LocalNationality(
    name = name,
)

fun RemoteNationality.Country.toLocal(
    name: String,
): LocalNationalityCountry = LocalNationalityCountry(
    nationalityName = name,
    probability = probability,
    locale = locale,
)
