package com.buggily.core.remote.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Converter

@Module
@InstallIn(SingletonComponent::class)
internal object JsonConverterFactoryProvider {

    @Provides
    @JsonConverterFactoryQualifier
    fun provides(
        @JsonContentTypeQualifier contentType: MediaType,
    ): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        return json.asConverterFactory(
            contentType = contentType,
        )
    }
}
