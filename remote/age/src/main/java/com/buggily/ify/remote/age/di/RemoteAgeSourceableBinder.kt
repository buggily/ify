package com.buggily.ify.remote.age.di

import com.buggily.ify.remote.age.RemoteAgeSource
import com.buggily.ify.remote.age.RemoteAgeSourceable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteAgeSourceableBinder {

    @Binds
    fun binds(
        remoteAgeSource: RemoteAgeSource,
    ): RemoteAgeSourceable
}
