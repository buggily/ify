package com.buggily.ify.local.gender

import kotlinx.coroutines.flow.Flow

interface LocalGenderSourceable {
    fun getByName(name: String): Flow<LocalGender?>
    suspend fun insert(gender: LocalGender)
}
