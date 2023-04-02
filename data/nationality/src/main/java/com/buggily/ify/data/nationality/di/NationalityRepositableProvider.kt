package com.buggily.ify.data.nationality.di

import com.buggily.ify.data.nationality.NationalityRepositable
import com.buggily.ify.data.nationality.NationalityRepository
import com.buggily.ify.local.nationality.LocalNationalitySourceable
import com.buggily.ify.local.nationality.country.LocalNationalityCountrySourceable
import com.buggily.ify.remote.nationality.RemoteNationalitySourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NationalityRepositableProvider {

    @Provides
    fun provides(
        remoteNationalitySource: RemoteNationalitySourceable,
        localNationalitySource: LocalNationalitySourceable,
        localNationalityCountrySource: LocalNationalityCountrySourceable,
    ): NationalityRepositable = NationalityRepository(
        remoteNationalitySource = remoteNationalitySource,
        localNationalitySource = localNationalitySource,
        localNationalityCountrySource = localNationalityCountrySource,
    )
}
