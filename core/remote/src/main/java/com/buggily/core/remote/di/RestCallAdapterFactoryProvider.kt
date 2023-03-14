package com.buggily.core.remote.di

import com.buggily.core.remote.RestCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.CallAdapter

@Module
@InstallIn(SingletonComponent::class)
object RestCallAdapterFactoryProvider {

    @Provides
    @RestCallAdapterFactoryQualifier
    fun provides(): CallAdapter.Factory = RestCallAdapterFactory()
}
