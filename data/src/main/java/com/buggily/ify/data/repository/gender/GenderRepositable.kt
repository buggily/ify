package com.buggily.ify.data.repository.gender

import com.buggily.ify.data.rest.gender.GenderRest

interface GenderRepositable {
    suspend fun get(name: String): GenderRest
}
