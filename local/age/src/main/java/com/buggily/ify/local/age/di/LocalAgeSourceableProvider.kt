package com.buggily.ify.local.age.di

import com.buggily.ify.local.age.LocalAgeDao
import com.buggily.ify.local.age.LocalAgeSource
import com.buggily.ify.local.age.LocalAgeSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalAgeSourceableProvider {

    @Provides
    fun provides(
        localAgeDao: LocalAgeDao,
    ): LocalAgeSourceable = LocalAgeSource(
        localAgeDao = localAgeDao,
    )
}
