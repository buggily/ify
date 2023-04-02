package com.buggily.ify.core.domain.di

import com.buggily.ify.core.domain.Format
import com.buggily.ify.core.domain.FormatLowercase
import com.buggily.ify.core.domain.FormatNumber
import com.buggily.ify.core.domain.FormatProbability
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
object FormatProvider {

    @Provides
    fun provides(
        locale: Locale,
        formatLowercase: FormatLowercase,
        formatNumber: FormatNumber,
        formatProbability: FormatProbability,
    ): Format = Format(
        locale = locale,
        formatLowercase = formatLowercase,
        formatNumber = formatNumber,
        formatProbability = formatProbability,
    )
}
