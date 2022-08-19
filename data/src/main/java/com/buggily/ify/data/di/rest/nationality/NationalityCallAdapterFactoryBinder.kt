package com.buggily.ify.data.di.rest.nationality

import com.buggily.ify.data.nationality.Nationality
import com.buggily.ify.data.nationality.NationalityError
import com.buggily.ify.data.rest.RestCallAdapterFactory
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
        factory: RestCallAdapterFactory<Nationality, NationalityError>,
    ): CallAdapter.Factory
}
