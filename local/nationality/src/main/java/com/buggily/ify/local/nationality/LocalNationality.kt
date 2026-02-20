package com.buggily.ify.local.nationality

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalNationalityDao.TABLE_NAME)
data class LocalNationality(

    @PrimaryKey
    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = COUNT)
    val count: Int,
) {

    companion object {
        const val NAME = "name"
        const val COUNT = "count"
    }
}
