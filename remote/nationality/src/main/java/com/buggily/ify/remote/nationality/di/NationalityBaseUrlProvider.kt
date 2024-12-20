package com.buggily.ify.remote.nationality.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl

@Module
@InstallIn(SingletonComponent::class)
internal object NationalityBaseUrlProvider {

    @Provides
    @NationalityBaseUrlQualifier
    fun provides(): HttpUrl = HttpUrl.Builder()
        .scheme("https")
        .host("api.nationalize.io")
        .build()
}
