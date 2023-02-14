package com.buggily.ify.domain.gender.use

import com.buggily.ify.data.gender.repository.GenderRepositable

class GetGender(
    private val repository: GenderRepositable,
) {

    suspend operator fun invoke(
        name: String,
    ) = repository.get(
        name = name,
    )
}
