package com.buggily.ify.domain.gender

import com.buggily.ify.core.domain.Format
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.model.toBy
import com.buggily.ify.data.gender.GenderRepositable

class GetGenderByName(
    private val genderRepository: GenderRepositable,
    private val format: Format,
) {

    suspend operator fun invoke(
        name: String,
    ): DataResult<GenderUi> = genderRepository.getByName(
        name = name,
    ).toBy { it.toUi(format) }
}
