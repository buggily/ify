package com.buggily.ify.data.nationality.repository

import com.buggily.ify.data.nationality.source.NationalitySourceable

class NationalityRepository(
    private val source: NationalitySourceable,
) : NationalityRepositable {

    override suspend fun get(
        name: String,
    ) = source.get(
        name = name,
    )
}
