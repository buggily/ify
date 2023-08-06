package com.buggily.ify.local.nationality

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.buggily.ify.local.nationality.country.LocalNationalityCountry

@Entity(tableName = LocalNationalityDao.tableName)
data class LocalNationality(
    @PrimaryKey
    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = COUNT)
    val count: Int,
) {

    data class WithCountries(

        @Embedded
        val nationality: LocalNationality,

        @Relation(
            parentColumn = NAME,
            entityColumn = LocalNationalityCountry.NATIONALITY_NAME,
        )

        val countries: List<LocalNationalityCountry>,
    )

    companion object {
        const val NAME = "name"
        const val COUNT = "count"
    }
}
