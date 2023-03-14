package com.buggily.ify.remote.gender.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GenderRetrofitProvider {

    @Provides
    @GenderRetrofitQualifier
    fun provides(
        @GenderBaseUrlQualifier baseUrl: HttpUrl,
        builder: Retrofit.Builder,
    ): Retrofit = builder
        .baseUrl(baseUrl)
        .build()
}
