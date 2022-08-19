package com.buggily.ify.domain.use.gender

import com.buggily.ify.data.repository.gender.GenderRepositable

class GetGender(
    private val repository: GenderRepositable,
) {

    suspend operator fun invoke(name: String) = repository.get(name)
}
