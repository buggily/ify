package com.buggily.ify.data.source.nationality

import com.buggily.ify.data.rest.nationality.NationalityRest

interface NationalitySourceable {
    suspend fun get(name: String): NationalityRest
}
