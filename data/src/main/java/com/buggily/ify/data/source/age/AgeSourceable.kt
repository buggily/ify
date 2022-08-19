package com.buggily.ify.data.source.age

import com.buggily.ify.data.rest.age.AgeRest

interface AgeSourceable {
    suspend fun get(name: String): AgeRest
}
