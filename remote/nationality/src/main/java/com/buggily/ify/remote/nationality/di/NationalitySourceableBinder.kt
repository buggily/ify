package com.buggily.ify.remote.nationality.di

import com.buggily.ify.remote.nationality.RemoteNationalitySource
import com.buggily.ify.remote.nationality.RemoteNationalitySourceable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NationalitySourceableBinder {

    @Binds
    fun binds(
        remoteNationalitySource: RemoteNationalitySource,
    ): RemoteNationalitySourceable
}
