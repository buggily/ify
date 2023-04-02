package com.buggily.ify.remote.gender.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl

@Module
@InstallIn(SingletonComponent::class)
object GenderBaseUrlProvider {

    @Provides
    @GenderBaseUrlQualifier
    fun provides(): HttpUrl = HttpUrl.Builder()
        .scheme("https")
        .host("api.genderize.io")
        .build()
}
