package com.buggily.ify.local.nationality

import kotlinx.coroutines.flow.Flow

internal class LocalNationalitySource(
    private val localNationalityDao: LocalNationalityDao,
) : LocalNationalitySourceable {

    override fun getByName(
        name: String,
    ): Flow<LocalNationality.WithCountries?> = localNationalityDao.getByName(
        name = name,
    )

    override suspend fun insert(
        nationality: LocalNationality,
    ) = localNationalityDao.insert(
        nationality = nationality,
    )
}
