package com.buggily.ify.data.age.di.repository

import com.buggily.ify.data.age.repository.AgeRepository
import com.buggily.ify.data.age.source.AgeSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AgeRepositoryProvider {

    @Provides
    fun provides(
        source: AgeSourceable,
    ): AgeRepository = AgeRepository(
        source = source,
    )
}
