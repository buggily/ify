package com.buggily.ify.data.gender.repository

import com.buggily.ify.data.gender.source.GenderSourceable

class GenderRepository(
    private val source: GenderSourceable,
) : GenderRepositable {

    override suspend fun get(name: String) = source.get(name)
}
