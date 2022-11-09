package com.buggily.ify.remote.age.di

import com.buggily.core.remote.RestCallAdapterFactory
import com.buggily.ify.remote.age.Age
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AgeCallAdapterFactoryProvider {

    @Provides
    fun provides(): RestCallAdapterFactory<Age, Age.Error> = RestCallAdapterFactory()
}
