package com.buggily.ify.data.gender.source

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.gender.Gender

interface GenderSourceable {
    suspend fun get(name: String): Rest<Gender, Gender.Error>
}
