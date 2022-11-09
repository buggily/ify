package com.buggily.ify.domain.nationality.di.use

import com.buggily.ify.data.nationality.repository.NationalityRepositable
import com.buggily.ify.domain.nationality.use.GetNationality
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GetNationalityProvider {

    @Provides
    fun provides(
        repository: NationalityRepositable,
    ): GetNationality = GetNationality(
        repository = repository,
    )
}
