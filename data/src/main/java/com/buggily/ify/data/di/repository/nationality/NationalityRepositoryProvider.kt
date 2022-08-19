package com.buggily.ify.data.di.repository.nationality

import com.buggily.ify.data.repository.nationality.NationalityRepository
import com.buggily.ify.data.source.nationality.NationalitySourceable
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
