package com.buggily.ify.data.age.source

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.age.Age

interface AgeSourceable {
    suspend fun get(name: String): Rest<Age, Age.Error>
}
