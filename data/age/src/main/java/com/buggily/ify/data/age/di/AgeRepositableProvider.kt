package com.buggily.ify.data.age.di

import com.buggily.ify.data.age.AgeRepositable
import com.buggily.ify.data.age.AgeRepository
import com.buggily.ify.local.age.LocalAgeSourceable
import com.buggily.ify.remote.age.RemoteAgeSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AgeRepositableProvider {

    @Provides
    fun provides(
        remoteAgeSource: RemoteAgeSourceable,
        localAgeSource: LocalAgeSourceable,
    ): AgeRepositable = AgeRepository(
        remoteAgeSource = remoteAgeSource,
        localAgeSource = localAgeSource,
    )
}
