package com.buggily.ify.domain.di.use.nationality

import com.buggily.ify.data.repository.nationality.NationalityRepositable
import com.buggily.ify.domain.use.nationality.GetNationality
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
