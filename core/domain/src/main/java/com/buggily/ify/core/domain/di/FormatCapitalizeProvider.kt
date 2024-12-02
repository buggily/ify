package com.buggily.ify.core.domain.di

import com.buggily.ify.core.domain.FormatCapitalize
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
internal object FormatCapitalizeProvider {

    @Provides
    fun provides(
        locale: Locale,
    ): FormatCapitalize = FormatCapitalize(
        locale = locale,
    )
}