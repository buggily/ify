package com.buggily.ify.data.di.source.gender

import com.buggily.ify.data.source.gender.GenderSource
import com.buggily.ify.data.source.gender.GenderSourceable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GenderSourceableBinder {

    @Binds
    fun binds(
        source: GenderSource,
    ): GenderSourceable
}
