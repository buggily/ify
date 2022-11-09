package com.buggily.ify.data.gender.di.repository

import com.buggily.ify.data.gender.repository.GenderRepository
import com.buggily.ify.data.gender.source.GenderSourceable
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
