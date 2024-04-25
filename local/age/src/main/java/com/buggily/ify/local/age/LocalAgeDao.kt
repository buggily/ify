package com.buggily.ify.local.age

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalAgeDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE ${LocalAge.NAME} = :name")
    fun getByName(name: String): Flow<LocalAge?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(age: LocalAge)

    companion object {
        const val TABLE_NAME = "age"
    }
}
