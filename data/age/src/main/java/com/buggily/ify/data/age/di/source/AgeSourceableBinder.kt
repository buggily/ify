package com.buggily.ify.data.age.di.source

import com.buggily.ify.data.age.source.AgeSource
import com.buggily.ify.data.age.source.AgeSourceable
import dagger.Binds
import dagger.Module
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
