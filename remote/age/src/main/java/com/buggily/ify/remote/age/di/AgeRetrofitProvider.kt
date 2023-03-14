package com.buggily.ify.remote.age.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AgeRetrofitProvider {

    @Provides
    @AgeRetrofitQualifier
    fun provides(
        @AgeBaseUrlQualifier baseUrl: HttpUrl,
        builder: Retrofit.Builder,
    ): Retrofit = builder
        .baseUrl(baseUrl)
        .build()
}
