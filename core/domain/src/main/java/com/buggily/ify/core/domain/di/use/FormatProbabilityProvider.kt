package com.buggily.ify.core.domain.di.use

import com.buggily.ify.core.domain.use.FormatProbability
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FormatProbabilityProvider {

    @Provides
    fun provides(): FormatProbability = FormatProbability()
}
