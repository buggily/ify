package com.buggily.ify.data.di.repository.gender

import com.buggily.ify.data.repository.gender.GenderRepository
import com.buggily.ify.data.source.gender.GenderSource
import com.buggily.ify.data.source.gender.GenderSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GenderRepositoryProvider {

    @Provides
    fun provides(
        source: GenderSourceable,
    ): GenderRepository = GenderRepository(
        source = source,
    )
}
