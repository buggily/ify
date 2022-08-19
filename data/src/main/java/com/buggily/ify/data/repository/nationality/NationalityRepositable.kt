package com.buggily.ify.data.repository.nationality

import com.buggily.ify.data.rest.nationality.NationalityRest

interface NationalityRepositable {
    suspend fun get(name: String): NationalityRest
}
