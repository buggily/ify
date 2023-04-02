package com.buggily.ify.core.local

import com.buggily.ify.local.age.LocalAgeDao
import com.buggily.ify.local.gender.LocalGenderDao
import com.buggily.ify.local.nationality.LocalNationalityDao
import com.buggily.ify.local.nationality.country.LocalNationalityCountryDao

interface IfyDatabaseable {
    fun getLocalAgeDao(): LocalAgeDao
    fun getLocalGenderDao(): LocalGenderDao
    fun getLocalNationalityDao(): LocalNationalityDao
    fun getLocalNationalityCountryDao(): LocalNationalityCountryDao
}
