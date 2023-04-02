package com.buggily.ify.local.nationality

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalNationalityDao {

    @Transaction
    @Query("SELECT * FROM $tableName WHERE ${LocalNationality.NAME} = :name")
    fun getByName(name: String): Flow<LocalNationality.WithCountries?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nationality: LocalNationality)

    companion object {
        const val tableName = "nationality"
    }
}
