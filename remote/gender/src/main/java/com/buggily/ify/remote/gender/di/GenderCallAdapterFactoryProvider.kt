package com.buggily.ify.remote.gender.di

import com.buggily.core.remote.RestCallAdapterFactory
import com.buggily.ify.remote.gender.Gender
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GenderCallAdapterFactoryProvider {

    @Provides
    fun provides(): RestCallAdapterFactory<Gender, Gender.Error> = RestCallAdapterFactory()
}
