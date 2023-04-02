package com.buggily.ify.domain.age

import com.buggily.ify.core.model.DataResult
import com.buggily.ify.data.age.Age
import com.buggily.ify.data.age.AgeRepositable

class GetAgeByName(
    private val ageRepository: AgeRepositable,
) {

    suspend operator fun invoke(
        name: String,
    ): DataResult<Age> = ageRepository.getByName(
        name = name,
    )
}
