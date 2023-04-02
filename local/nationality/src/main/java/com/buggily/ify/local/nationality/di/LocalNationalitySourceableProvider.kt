package com.buggily.ify.local.nationality.di

import com.buggily.ify.local.nationality.LocalNationalityDao
import com.buggily.ify.local.nationality.LocalNationalitySource
import com.buggily.ify.local.nationality.LocalNationalitySourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalNationalitySourceableProvider {

    @Provides
    fun provides(
        localNationalityDao: LocalNationalityDao,
    ): LocalNationalitySourceable = LocalNationalitySource(
        localNationalityDao = localNationalityDao,
    )
}
