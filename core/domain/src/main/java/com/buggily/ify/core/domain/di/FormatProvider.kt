package com.buggily.ify.core.domain.di

import com.buggily.ify.core.domain.Format
import com.buggily.ify.core.domain.FormatNumber
import com.buggily.ify.core.domain.FormatProbability
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
internal object FormatProvider {

    @Provides
    fun provides(
        locale: Locale,
        formatNumber: FormatNumber,
        formatProbability: FormatProbability,
    ): Format = Format(
        locale = locale,
        formatNumber = formatNumber,
        formatProbability = formatProbability,
    )
}
