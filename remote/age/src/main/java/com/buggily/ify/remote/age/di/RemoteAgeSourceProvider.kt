package com.buggily.ify.remote.age.di

import com.buggily.ify.remote.age.RemoteAgeServiceable
import com.buggily.ify.remote.age.RemoteAgeSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteAgeSourceProvider {

    @Provides
    fun provides(
        remoteAgeService: RemoteAgeServiceable,
    ): RemoteAgeSource = RemoteAgeSource(
        remoteAgeService = remoteAgeService,
    )
}
