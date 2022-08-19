package com.buggily.ify.data.di.rest

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.MediaType
import retrofit2.Converter

@Module
@InstallIn(SingletonComponent::class)
object JsonConverterFactoryProvider {

    @Provides
    @JsonConverterFactoryQualifier
    fun provides(
        @JsonContentTypeQualifier contentType: MediaType,
    ): Converter.Factory = Json.asConverterFactory(
        contentType = contentType,
    )
}
