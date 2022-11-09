package com.buggily.ify.remote.gender.di

import com.buggily.core.remote.di.JsonConverterFactoryQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GenderRetrofitProvider {

    @Provides
    @GenderRetrofitQualifier
    fun provides(
        @GenderBaseUrlQualifier baseUrl: HttpUrl,
        @JsonConverterFactoryQualifier converterFactory: Converter.Factory,
        @GenderCallAdapterFactoryQualifier callAdapterFactory: CallAdapter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()
}
