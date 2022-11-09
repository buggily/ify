package com.buggily.ify.data.nationality.di.repository

import com.buggily.ify.data.nationality.repository.NationalityRepository
import com.buggily.ify.data.nationality.source.NationalitySourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NationalityRepositoryProvider {

    @Provides
    fun provides(
        source: NationalitySourceable,
    ): NationalityRepository = NationalityRepository(
        source = source,
    )
}
