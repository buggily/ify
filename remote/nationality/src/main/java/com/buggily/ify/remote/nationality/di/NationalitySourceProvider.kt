package com.buggily.ify.remote.nationality.di

import com.buggily.ify.remote.nationality.RemoteNationalityServiceable
import com.buggily.ify.remote.nationality.RemoteNationalitySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NationalitySourceProvider {

    @Provides
    fun provides(
        remoteNationalityService: RemoteNationalityServiceable,
    ): RemoteNationalitySource = RemoteNationalitySource(
        remoteNationalityService = remoteNationalityService,
    )
}
