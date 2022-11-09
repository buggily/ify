package com.buggily.ify.data.age.di.repository

import com.buggily.ify.data.age.repository.AgeRepositable
import com.buggily.ify.data.age.repository.AgeRepository
import dagger.Binds
import dagger.Module
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
