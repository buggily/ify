package com.buggily.ify.data.source.nationality

import com.buggily.ify.data.rest.nationality.NationalityServiceable

class NationalitySource(
    private val service: NationalityServiceable,
) : NationalitySourceable {

    override suspend fun get(name: String) = service.get(name)
}
