package com.buggily.ify.data.di.rest.age

import com.buggily.ify.data.rest.RestCallAdapterFactory
import com.buggily.ify.data.age.Age
import com.buggily.ify.data.age.AgeError
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.CallAdapter

@Module
@InstallIn(SingletonComponent::class)
interface AgeCallAdapterFactoryBinder {

    @Binds
    @AgeCallAdapterFactoryQualifier
    fun binds(
        factory: RestCallAdapterFactory<Age, AgeError>,
    ): CallAdapter.Factory
}
