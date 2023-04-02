package com.buggily.ify.data.nationality

import com.buggily.ify.core.model.DataResult

interface NationalityRepositable {
    suspend fun get(name: String): DataResult<Nationality>
}
