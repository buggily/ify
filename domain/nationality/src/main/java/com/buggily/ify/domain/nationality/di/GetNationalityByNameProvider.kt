package com.buggily.ify.domain.nationality.di

import com.buggily.ify.data.nationality.NationalityRepositable
import com.buggily.ify.domain.nationality.GetNationalityByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GetNationalityByNameProvider {

    @Provides
    fun provides(
        nationalityRepository: NationalityRepositable,
    ): GetNationalityByName = GetNationalityByName(
        nationalityRepository = nationalityRepository,
    )
}
