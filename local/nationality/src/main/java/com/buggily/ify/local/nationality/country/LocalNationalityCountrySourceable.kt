package com.buggily.ify.local.nationality.country

interface LocalNationalityCountrySourceable {
    suspend fun insert(countries: List<LocalNationalityCountry>)
}
