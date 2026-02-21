package com.buggily.ify.local.nationality

import androidx.room.Embedded
import androidx.room.Relation
import com.buggily.ify.local.nationality.LocalNationality.Companion.NAME
import com.buggily.ify.local.nationality.country.LocalNationalityCountry

data class LocalNationalityWithCountries(

    @Embedded
    val nationality: LocalNationality,

    @Relation(
        parentColumn = NAME,
        entityColumn = LocalNationalityCountry.NATIONALITY_NAME,
    )

    val countries: List<LocalNationalityCountry>,
)
