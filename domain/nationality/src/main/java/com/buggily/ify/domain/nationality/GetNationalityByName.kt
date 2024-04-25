package com.buggily.ify.domain.nationality

import com.buggily.ify.core.domain.Format
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.model.toBy
import com.buggily.ify.data.nationality.NationalityRepositable

class GetNationalityByName(
    private val nationalityRepository: NationalityRepositable,
    private val format: Format,
) {

    suspend operator fun invoke(
        name: String,
    ): DataResult<NationalityUi> = nationalityRepository.getByName(
        name = name,
    ).toBy { it.toUi(format) }
}
