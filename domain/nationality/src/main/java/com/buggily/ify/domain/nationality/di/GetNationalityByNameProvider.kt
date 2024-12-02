package com.buggily.ify.domain.nationality.di

import com.buggily.ify.core.domain.Format
import com.buggily.ify.data.nationality.NationalityRepositable
import com.buggily.ify.domain.nationality.GetNationalityByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object GetNationalityByNameProvider {

    @Provides
    fun provides(
        nationalityRepository: NationalityRepositable,
        format: Format,
    ): GetNationalityByName = GetNationalityByName(
        nationalityRepository = nationalityRepository,
        format = format,
    )
}
