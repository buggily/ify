package com.buggily.ify.local.nationality.country

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.buggily.ify.local.nationality.LocalNationalityDao

@Dao
interface LocalNationalityCountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countries: List<LocalNationalityCountry>)

    companion object {
        const val TABLE_NAME = "${LocalNationalityDao.TABLE_NAME}_country"
    }
}
