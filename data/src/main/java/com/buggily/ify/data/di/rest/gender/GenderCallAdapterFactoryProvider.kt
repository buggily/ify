package com.buggily.ify.data.di.rest.gender

import com.buggily.ify.data.gender.Gender
import com.buggily.ify.data.gender.GenderError
import com.buggily.ify.data.rest.RestCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GenderCallAdapterFactoryProvider {

    @Provides
    fun provides(): RestCallAdapterFactory<Gender, GenderError> = RestCallAdapterFactory()
}
