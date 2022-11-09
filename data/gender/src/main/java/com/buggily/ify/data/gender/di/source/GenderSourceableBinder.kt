package com.buggily.ify.data.gender.di.source

import com.buggily.ify.data.gender.source.GenderSource
import com.buggily.ify.data.gender.source.GenderSourceable
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
