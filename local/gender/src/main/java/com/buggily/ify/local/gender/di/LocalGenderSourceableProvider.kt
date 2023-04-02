package com.buggily.ify.local.gender.di

import com.buggily.ify.local.gender.LocalGenderDao
import com.buggily.ify.local.gender.LocalGenderSource
import com.buggily.ify.local.gender.LocalGenderSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalGenderSourceableProvider {

    @Provides
    fun provides(
        localGenderDao: LocalGenderDao,
    ): LocalGenderSourceable = LocalGenderSource(
        localGenderDao = localGenderDao,
    )
}
