package com.buggily.ify.data.gender.repository

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.gender.Gender


interface GenderRepositable {
    suspend fun get(name: String): Rest<Gender, Gender.Error>
}
