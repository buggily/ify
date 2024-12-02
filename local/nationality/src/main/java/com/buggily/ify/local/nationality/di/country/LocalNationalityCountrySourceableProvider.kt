package com.buggily.ify.local.nationality.di.country

import com.buggily.ify.local.nationality.country.LocalNationalityCountryDao
import com.buggily.ify.local.nationality.country.LocalNationalityCountrySource
import com.buggily.ify.local.nationality.country.LocalNationalityCountrySourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalNationalityCountrySourceableProvider {

    @Provides
    fun provides(
        localNationalityCountryDao: LocalNationalityCountryDao,
    ): LocalNationalityCountrySourceable = LocalNationalityCountrySource(
        localNationalityCountryDao = localNationalityCountryDao,
    )
}
