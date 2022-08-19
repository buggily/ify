package com.buggily.ify.data.repository.age

import com.buggily.ify.data.rest.age.AgeRest

interface AgeRepositable {
    suspend fun get(name: String): AgeRest
}
