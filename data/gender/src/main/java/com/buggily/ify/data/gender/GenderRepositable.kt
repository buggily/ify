package com.buggily.ify.data.gender

import com.buggily.ify.core.model.DataResult


interface GenderRepositable {
    suspend fun get(name: String): DataResult<Gender>
}
