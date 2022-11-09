package com.buggily.ify.core.domain.di.use

import com.buggily.ify.core.domain.use.Format
import com.buggily.ify.core.domain.use.FormatLowercase
import com.buggily.ify.core.domain.use.FormatNumber
import com.buggily.ify.core.domain.use.FormatProbability
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
