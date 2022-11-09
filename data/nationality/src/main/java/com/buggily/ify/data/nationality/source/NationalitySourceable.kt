package com.buggily.ify.data.nationality.source

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.nationality.Nationality

interface NationalitySourceable {
    suspend fun get(name: String): Rest<Nationality, Nationality.Error>
}
