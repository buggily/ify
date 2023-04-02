package com.buggily.ify.domain.gender

import com.buggily.ify.core.model.DataResult
import com.buggily.ify.data.gender.Gender
import com.buggily.ify.data.gender.GenderRepositable

class GetGenderByName(
    private val genderRepository: GenderRepositable,
) {

    suspend operator fun invoke(
        name: String,
    ): DataResult<Gender> = genderRepository.get(
        name = name,
    )
}
