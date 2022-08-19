package com.buggily.ify.data.di.repository.age

import com.buggily.ify.data.repository.age.AgeRepositable
import com.buggily.ify.data.repository.age.AgeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AgeRepositableBinder {

    @Binds
    fun binds(
        repository: AgeRepository,
    ): AgeRepositable
}
