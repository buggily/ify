package com.buggily.ify.local.nationality.country

internal class LocalNationalityCountrySource(
    private val localNationalityCountryDao: LocalNationalityCountryDao,
) : LocalNationalityCountrySourceable {

    override suspend fun insert(
        countries: List<LocalNationalityCountry>,
    ) = localNationalityCountryDao.insert(
        countries = countries,
    )
}
