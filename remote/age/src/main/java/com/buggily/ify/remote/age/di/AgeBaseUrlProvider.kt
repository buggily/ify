package com.buggily.ify.remote.age.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl

@Module
@InstallIn(SingletonComponent::class)
object AgeBaseUrlProvider {

    @Provides
    @AgeBaseUrlQualifier
    fun provides(): HttpUrl = HttpUrl.Builder()
        .scheme("https")
        .host("api.agify.io")
        .build()
}
