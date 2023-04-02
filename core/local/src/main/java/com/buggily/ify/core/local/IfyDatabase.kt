package com.buggily.ify.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.buggily.ify.local.age.LocalAge
import com.buggily.ify.local.age.LocalAgeDao
import com.buggily.ify.local.gender.LocalGender
import com.buggily.ify.local.gender.LocalGenderDao
import com.buggily.ify.local.nationality.LocalNationality
import com.buggily.ify.local.nationality.LocalNationalityDao
import com.buggily.ify.local.nationality.country.LocalNationalityCountry
import com.buggily.ify.local.nationality.country.LocalNationalityCountryDao

@Database(

    entities = [
        LocalAge::class,
        LocalGender::class,
        LocalNationality::class,
        LocalNationalityCountry::class,
    ],

    exportSchema = false,
    version = 1,
)

abstract class IfyDatabase : RoomDatabase(), IfyDatabaseable {
    abstract override fun getLocalAgeDao(): LocalAgeDao
    abstract override fun getLocalGenderDao(): LocalGenderDao
    abstract override fun getLocalNationalityDao(): LocalNationalityDao
    abstract override fun getLocalNationalityCountryDao(): LocalNationalityCountryDao
}
