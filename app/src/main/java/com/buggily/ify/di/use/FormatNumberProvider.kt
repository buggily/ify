package com.buggily.ify.di.use

import com.buggily.ify.use.FormatNumber
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat

@Module
@InstallIn(SingletonComponent::class)
object FormatNumberProvider {

    @Provides
    fun provides(
        numberFormat: NumberFormat,
    ): FormatNumber = FormatNumber(
        numberFormat = numberFormat,
    )
}
