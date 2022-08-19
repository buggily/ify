package com.buggily.ify.data.di.source.age

import com.buggily.ify.data.source.age.AgeSource
import com.buggily.ify.data.source.age.AgeSourceable
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AgeSourceableBinder {

    @Binds
    fun binds(
        source: AgeSource,
    ): AgeSourceable
}
