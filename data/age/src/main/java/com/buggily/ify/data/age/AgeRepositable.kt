package com.buggily.ify.data.age

import com.buggily.ify.core.model.DataResult

interface AgeRepositable {
    suspend fun getByName(name: String): DataResult<Age>
}
