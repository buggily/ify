package com.buggily.core.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

@Module
@InstallIn(SingletonComponent::class)
internal object JsonContentTypeProvider {

    @Provides
    @JsonContentTypeQualifier
    fun provides(): MediaType = "application/json".toMediaType()
}
