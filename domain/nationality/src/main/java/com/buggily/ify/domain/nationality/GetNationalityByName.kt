package com.buggily.ify.domain.nationality

import com.buggily.ify.data.nationality.NationalityRepositable

class GetNationalityByName(
    private val nationalityRepository: NationalityRepositable,
) {

    suspend operator fun invoke(
        name: String,
    ) = nationalityRepository.get(
        name = name,
    )
}
