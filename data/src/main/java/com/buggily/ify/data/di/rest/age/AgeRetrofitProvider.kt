package com.buggily.ify.data.di.rest.age

import com.buggily.ify.data.di.rest.JsonConverterFactoryQualifier
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
object AgeRetrofitProvider {

    @Provides
    @AgeRetrofitQualifier
    fun provides(
        @AgeBaseUrlQualifier baseUrl: HttpUrl,
        @JsonConverterFactoryQualifier converterFactory: Converter.Factory,
        @AgeCallAdapterFactoryQualifier callAdapterFactory: CallAdapter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()
}
