package com.buggily.ify.core.local.di.nationality

import com.buggily.ify.core.local.IfyDatabaseable
import com.buggily.ify.local.nationality.LocalNationalityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalNationalityDaoProvider {

    @Provides
    fun provides(
        ifyDatabase: IfyDatabaseable,
    ): LocalNationalityDao = ifyDatabase.getLocalNationalityDao()
}
