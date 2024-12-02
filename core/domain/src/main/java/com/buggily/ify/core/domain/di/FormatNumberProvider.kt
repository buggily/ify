package com.buggily.ify.core.domain.di

import com.buggily.ify.core.domain.FormatNumber
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat

@Module
@InstallIn(SingletonComponent::class)
internal object FormatNumberProvider {

    @Provides
    fun provides(
        numberFormat: NumberFormat,
    ): FormatNumber = FormatNumber(
        numberFormat = numberFormat,
    )
}
