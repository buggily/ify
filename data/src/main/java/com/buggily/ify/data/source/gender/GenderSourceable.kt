package com.buggily.ify.data.source.gender

import com.buggily.ify.data.rest.gender.GenderRest

interface GenderSourceable {
    suspend fun get(name: String): GenderRest
}
