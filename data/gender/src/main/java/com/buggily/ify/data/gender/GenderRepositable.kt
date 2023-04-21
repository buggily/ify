package com.buggily.ify.data.gender

import com.buggily.ify.core.model.DataResult


interface GenderRepositable {
    suspend fun getByName(name: String): DataResult<Gender>
}
