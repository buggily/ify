package com.buggily.ify.core.local.di.nationality.country

import com.buggily.ify.core.local.IfyDatabaseable
import com.buggily.ify.local.nationality.country.LocalNationalityCountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalNationalityCountryDaoProvider {

    @Provides
    fun provides(
        ifyDatabase: IfyDatabaseable,
    ): LocalNationalityCountryDao = ifyDatabase.getLocalNationalityCountryDao()
}
