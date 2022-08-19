package com.buggily.ify.data.di.repository.age

import com.buggily.ify.data.repository.age.AgeRepository
import com.buggily.ify.data.source.age.AgeSourceable
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
