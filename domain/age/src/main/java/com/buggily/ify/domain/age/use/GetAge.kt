package com.buggily.ify.domain.age.use

import com.buggily.ify.data.age.repository.AgeRepositable

class GetAge(
    private val repository: AgeRepositable,
) {

    suspend operator fun invoke(
        name: String,
    ) = repository.get(
        name = name,
    )
}
