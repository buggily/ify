package com.buggily.ify.core.domain.di

import com.buggily.ify.core.domain.FormatUppercase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
internal object FormatUppercaseProvider {

    @Provides
    fun provides(
        locale: Locale,
    ): FormatUppercase = FormatUppercase(
        locale = locale,
    )
}
