package com.buggily.core.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType

@Module
@InstallIn(SingletonComponent::class)
internal object JsonContentTypeProvider {

    @Provides
    @JsonContentTypeQualifier
    fun provides(): MediaType = MediaType.get("application/json")
}
