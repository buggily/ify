package com.buggily.ify.remote.gender.di

import com.buggily.ify.remote.gender.RemoteGenderServiceable
import com.buggily.ify.remote.gender.RemoteGenderSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteGenderSourceProvider {

    @Provides
    fun provides(
        remoteGenderService: RemoteGenderServiceable,
    ): RemoteGenderSource = RemoteGenderSource(
        remoteGenderService = remoteGenderService,
    )
}
