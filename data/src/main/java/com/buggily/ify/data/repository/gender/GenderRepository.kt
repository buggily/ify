package com.buggily.ify.data.repository.gender

import com.buggily.ify.data.source.gender.GenderSourceable

class GenderRepository(
    private val source: GenderSourceable,
) : GenderRepositable {

    override suspend fun get(name: String) = source.get(name)
}
