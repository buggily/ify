package com.buggily.ify.data.source.gender

import com.buggily.ify.data.rest.gender.GenderRest
import com.buggily.ify.data.rest.gender.GenderServiceable

class GenderSource(
    private val service: GenderServiceable,
) : GenderSourceable {

    override suspend fun get(name: String): GenderRest = service.get(name)
}
