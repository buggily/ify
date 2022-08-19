package com.buggily.ify.data.source.age

import com.buggily.ify.data.rest.age.AgeRest
import com.buggily.ify.data.rest.age.AgeServiceable

class AgeSource(
    private val service: AgeServiceable,
) : AgeSourceable {

    override suspend fun get(name: String): AgeRest = service.get(name)
}
