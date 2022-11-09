package com.buggily.ify.data.age.repository

import com.buggily.ify.data.age.source.AgeSourceable

class AgeRepository(
    private val source: AgeSourceable,
) : AgeRepositable {

    override suspend fun get(name: String) = source.get(name)
}
