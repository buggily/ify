package com.buggily.ify.data.nationality.repository

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.nationality.Nationality

interface NationalityRepositable {
    suspend fun get(name: String): Rest<Nationality, Nationality.Error>
}
