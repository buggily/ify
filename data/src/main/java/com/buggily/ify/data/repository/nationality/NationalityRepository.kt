package com.buggily.ify.data.repository.nationality

import com.buggily.ify.data.source.nationality.NationalitySourceable

class NationalityRepository(
    private val source: NationalitySourceable,
) : NationalityRepositable {

    override suspend fun get(name: String) = source.get(name)
}
