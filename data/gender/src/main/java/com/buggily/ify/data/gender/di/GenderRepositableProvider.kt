package com.buggily.ify.data.gender.di

import com.buggily.ify.data.gender.GenderRepositable
import com.buggily.ify.data.gender.GenderRepository
import com.buggily.ify.local.gender.LocalGenderSourceable
import com.buggily.ify.remote.gender.RemoteGenderSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object GenderRepositableProvider {

    @Provides
    fun provides(
        remoteGenderSource: RemoteGenderSourceable,
        localGenderSource: LocalGenderSourceable,
    ): GenderRepositable = GenderRepository(
        remoteGenderSource = remoteGenderSource,
        localGenderSource = localGenderSource,
    )
}
