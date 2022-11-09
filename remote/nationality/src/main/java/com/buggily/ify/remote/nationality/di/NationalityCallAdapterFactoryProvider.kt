package com.buggily.ify.remote.nationality.di

import com.buggily.core.remote.RestCallAdapterFactory
import com.buggily.ify.remote.nationality.Nationality
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NationalityCallAdapterFactoryProvider {

    @Provides
    fun provides(): RestCallAdapterFactory<Nationality, Nationality.Error> = RestCallAdapterFactory()
}
