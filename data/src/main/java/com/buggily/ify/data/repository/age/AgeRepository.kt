package com.buggily.ify.data.repository.age

import com.buggily.ify.data.rest.age.AgeRest
import com.buggily.ify.data.source.age.AgeSourceable

class AgeRepository(
    private val source: AgeSourceable,
) : AgeRepositable {

    override suspend fun get(name: String): AgeRest = source.get(name)
}
