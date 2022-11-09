package com.buggily.ify.data.age.repository

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.age.Age

interface AgeRepositable {
    suspend fun get(name: String): Rest<Age, Age.Error>
}
