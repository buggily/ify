package com.buggily.ify.local.nationality

import kotlinx.coroutines.flow.Flow

interface LocalNationalitySourceable {
    fun getByName(name: String): Flow<LocalNationality.WithCountries?>
    suspend fun insert(nationality: LocalNationality)
}
