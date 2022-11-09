package com.buggily.ify.data.gender.di.repository

import com.buggily.ify.data.gender.repository.GenderRepositable
import com.buggily.ify.data.gender.repository.GenderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GenderRepositableBinder {

    @Binds
    fun binds(
        repository: GenderRepository,
    ): GenderRepositable
}
