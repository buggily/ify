package com.buggily.ify.domain.use.age

import com.buggily.ify.data.repository.age.AgeRepositable

class GetAge(
    private val repository: AgeRepositable,
) {

    suspend operator fun invoke(name: String) = repository.get(name)
}
