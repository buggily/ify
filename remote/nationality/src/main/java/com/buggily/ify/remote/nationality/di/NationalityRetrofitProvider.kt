package com.buggily.ify.remote.nationality.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object NationalityRetrofitProvider {

    @Provides
    @NationalityRetrofitQualifier
    fun provides(

        @NationalityBaseUrlQualifier
        baseUrl: HttpUrl,

        builder: Retrofit.Builder,
    ): Retrofit = builder
        .baseUrl(baseUrl)
        .build()
}
