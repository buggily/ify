package com.buggily.ify.remote.nationality.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl

@Module
@InstallIn(SingletonComponent::class)
object NationalityBaseUrlProvider {

    @Provides
    @NationalityBaseUrlQualifier
    fun provides(): HttpUrl = HttpUrl.get("https://api.nationalize.io/")
}
