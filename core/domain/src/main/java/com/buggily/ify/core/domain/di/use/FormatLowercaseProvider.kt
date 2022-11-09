package com.buggily.ify.core.domain.di.use

import com.buggily.ify.core.domain.use.FormatLowercase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
object FormatLowercaseProvider {

    @Provides
    fun provides(
        locale: Locale,
    ): FormatLowercase = FormatLowercase(
        locale = locale,
    )
}
