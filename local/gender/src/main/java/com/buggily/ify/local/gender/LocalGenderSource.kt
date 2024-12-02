package com.buggily.ify.local.gender

import kotlinx.coroutines.flow.Flow

internal class LocalGenderSource(
    private val localGenderDao: LocalGenderDao,
) : LocalGenderSourceable {

    override fun getByName(
        name: String,
    ): Flow<LocalGender?> = localGenderDao.getByName(
        name = name,
    )

    override suspend fun insert(
        gender: LocalGender,
    ) = localGenderDao.insert(
        gender = gender,
    )
}
