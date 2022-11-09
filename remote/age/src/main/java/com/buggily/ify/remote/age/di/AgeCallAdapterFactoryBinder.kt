package com.buggily.ify.remote.age.di

import com.buggily.core.remote.RestCallAdapterFactory
import com.buggily.ify.remote.age.Age
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.CallAdapter

@Module
@InstallIn(SingletonComponent::class)
interface AgeCallAdapterFactoryBinder {

    @Binds
    @AgeCallAdapterFactoryQualifier
    fun binds(
        factory: RestCallAdapterFactory<Age, Age.Error>,
    ): CallAdapter.Factory
}
