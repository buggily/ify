package com.buggily.ify.remote.nationality.di

import com.buggily.core.remote.RestCallAdapterFactory
import com.buggily.ify.remote.nationality.Nationality
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.CallAdapter

@Module
@InstallIn(SingletonComponent::class)
interface NationalityCallAdapterFactoryBinder {

    @Binds
    @NationalityCallAdapterFactoryQualifier
    fun binds(
        factory: RestCallAdapterFactory<Nationality, Nationality.Error>,
    ): CallAdapter.Factory
}
