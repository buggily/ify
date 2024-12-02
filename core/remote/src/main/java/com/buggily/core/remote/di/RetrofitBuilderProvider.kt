package com.buggily.core.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitBuilderProvider {

    @Provides
    fun provides(

        @JsonConverterFactoryQualifier
        converterFactory: Converter.Factory,

        @RestCallAdapterFactoryQualifier
        callAdapterFactory: CallAdapter.Factory,
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
}
