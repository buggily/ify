package com.buggily.ify.domain.age

import com.buggily.ify.core.domain.FormatNumber
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.model.toBy
import com.buggily.ify.data.age.AgeRepositable

class GetAgeByName(
    private val ageRepository: AgeRepositable,
    private val formatNumber: FormatNumber,
) {

    suspend operator fun invoke(
        name: String,
    ): DataResult<AgeUi> = ageRepository.getByName(
        name = name,
    ).toBy { it.toUi(formatNumber) }
}
