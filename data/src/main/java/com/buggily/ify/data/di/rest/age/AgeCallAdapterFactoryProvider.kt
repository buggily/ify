package com.buggily.ify.data.di.rest.age

import com.buggily.ify.data.age.Age
import com.buggily.ify.data.age.AgeError
import com.buggily.ify.data.rest.RestCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AgeCallAdapterFactoryProvider {

    @Provides
    fun provides(): RestCallAdapterFactory<Age, AgeError> = RestCallAdapterFactory()
}
