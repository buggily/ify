package com.buggily.ify.local.age

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = LocalAgeDao.tableName,
)

data class LocalAge(

    @PrimaryKey
    @ColumnInfo(name  = NAME)
    val name: String,

    @ColumnInfo(name = AGE)
    val age: Int?,

    @ColumnInfo(name = COUNT)
    val count: Int,
) {

    companion object {
        const val NAME = "name"
        const val AGE = "age"
        const val COUNT = "count"
    }
}
