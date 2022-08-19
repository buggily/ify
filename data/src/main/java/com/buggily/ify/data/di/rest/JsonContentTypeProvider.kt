package com.buggily.ify.data.di.rest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType

@Module
@InstallIn(SingletonComponent::class)
object JsonContentTypeProvider {

    @Provides
    @JsonContentTypeQualifier
    fun provides(): MediaType = MediaType.get("application/json")
}
