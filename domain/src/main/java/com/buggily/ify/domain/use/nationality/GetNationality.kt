package com.buggily.ify.domain.use.nationality

import com.buggily.ify.data.repository.nationality.NationalityRepositable

class GetNationality(
    private val repository: NationalityRepositable,
) {

    suspend operator fun invoke(name: String) = repository.get(name)
}
