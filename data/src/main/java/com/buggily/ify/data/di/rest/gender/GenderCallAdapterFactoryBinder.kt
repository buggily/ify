package com.buggily.ify.data.di.rest.gender

import com.buggily.ify.data.gender.Gender
import com.buggily.ify.data.gender.GenderError
import com.buggily.ify.data.rest.RestCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.CallAdapter

@Module
@InstallIn(SingletonComponent::class)
interface GenderCallAdapterFactoryBinder {

    @Binds
    @GenderCallAdapterFactoryQualifier
    fun binds(
        factory: RestCallAdapterFactory<Gender, GenderError>,
    ): CallAdapter.Factory
}
