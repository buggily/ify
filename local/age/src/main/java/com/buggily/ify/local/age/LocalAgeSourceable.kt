package com.buggily.ify.local.age

import kotlinx.coroutines.flow.Flow

interface LocalAgeSourceable {
    fun getByName(name: String): Flow<LocalAge?>
    suspend fun insert(age: LocalAge)
}
