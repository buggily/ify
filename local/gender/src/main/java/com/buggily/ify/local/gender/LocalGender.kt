package com.buggily.ify.local.gender

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalGenderDao.tableName)
data class LocalGender(

    @PrimaryKey
    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = GENDER)
    val gender: Gender?,

    @ColumnInfo(name = PROBABILITY)
    val probability: Float,

    @ColumnInfo(name = COUNT)
    val count: Int,
) {

    enum class Gender {
        Male,
        Female,
    }

    companion object {
        const val NAME = "name"
        const val GENDER = "gender"
        const val PROBABILITY = "probability"
        const val COUNT = "count"
    }
}
