package com.buggily.ify.local.nationality.country

import androidx.room.TypeConverter
import java.util.Locale

class LocaleTypeConverter {

    @TypeConverter
    fun to(string: String): Locale = Locale.Builder()
        .setRegion(string)
        .build()

    @TypeConverter
    fun from(locale: Locale): String = locale.country
}
