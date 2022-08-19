package com.buggily.ify.data.di.rest.gender

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
    fun provides(): HttpUrl = HttpUrl.get(BASE_URL)

    private const val BASE_URL = "https://api.genderize.io"
}
