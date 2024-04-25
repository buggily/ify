package com.buggily.ify.local.gender

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalGenderDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE ${LocalGender.NAME} = :name")
    fun getByName(name: String): Flow<LocalGender?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gender: LocalGender)

    companion object {
        const val TABLE_NAME = "gender"
    }
}
