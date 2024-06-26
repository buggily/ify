package com.buggily.ify.local.nationality.country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.buggily.ify.local.nationality.LocalNationalityDao
import java.util.Locale

@Entity(

    tableName = LocalNationalityCountryDao.TABLE_NAME,

    primaryKeys = [
        LocalNationalityCountry.NATIONALITY_NAME,
        LocalNationalityCountry.LOCALE,
    ],
)

@TypeConverters(
    LocaleTypeConverter::class,
)

data class LocalNationalityCountry(

    @ColumnInfo(name = NATIONALITY_NAME)
    val nationalityName: String,

    @ColumnInfo(name = LOCALE)
    val locale: Locale,

    @ColumnInfo(name = PROBABILITY)
    val probability: Float,
) {

    companion object {
        const val NATIONALITY_NAME = "${LocalNationalityDao.TABLE_NAME}_name"
        const val PROBABILITY = "probability"
        const val LOCALE = "locale"
    }
}
