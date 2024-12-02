package com.buggily.ify.local.age

import kotlinx.coroutines.flow.Flow

internal class LocalAgeSource(
    private val localAgeDao: LocalAgeDao,
) : LocalAgeSourceable {

    override fun getByName(
        name: String,
    ): Flow<LocalAge?> = localAgeDao.getByName(
        name = name,
    )

    override suspend fun insert(
        age: LocalAge,
    ) = localAgeDao.insert(
        age = age,
    )
}
