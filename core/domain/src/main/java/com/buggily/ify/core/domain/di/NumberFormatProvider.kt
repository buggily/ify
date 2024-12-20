package com.buggily.ify.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
internal object NumberFormatProvider {

    @Provides
    fun provides(
        locale: Locale,
    ): NumberFormat = NumberFormat.getInstance(locale)
}
