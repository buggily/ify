package com.buggily.ify.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
internal object LocaleProvider {

    @Provides
    fun provides(): Locale = Locale.getDefault()
}
