package com.buggily.ify.data.di.repository.gender

import com.buggily.ify.data.repository.gender.GenderRepositable
import com.buggily.ify.data.repository.gender.GenderRepository
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
