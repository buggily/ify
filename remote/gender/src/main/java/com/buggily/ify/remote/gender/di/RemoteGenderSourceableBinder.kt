package com.buggily.ify.remote.gender.di

import com.buggily.ify.remote.gender.RemoteGenderSource
import com.buggily.ify.remote.gender.RemoteGenderSourceable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteGenderSourceableBinder {

    @Binds
    fun binds(
        remoteGenderSource: RemoteGenderSource,
    ): RemoteGenderSourceable
}
