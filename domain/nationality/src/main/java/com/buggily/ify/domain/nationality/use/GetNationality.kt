package com.buggily.ify.domain.nationality.use

import com.buggily.ify.data.nationality.repository.NationalityRepositable

class GetNationality(
    private val repository: NationalityRepositable,
) {

    suspend operator fun invoke(name: String) = repository.get(name)
}
