package com.buggily.ify.data.di.rest.nationality

import com.buggily.ify.data.nationality.Nationality
import com.buggily.ify.data.nationality.NationalityError
import com.buggily.ify.data.rest.RestCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NationalityCallAdapterFactoryProvider {

    @Provides
    fun provides(): RestCallAdapterFactory<Nationality, NationalityError> = RestCallAdapterFactory()
}
