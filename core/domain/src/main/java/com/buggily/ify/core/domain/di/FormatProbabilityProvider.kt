package com.buggily.ify.core.domain.di

import com.buggily.ify.core.domain.FormatProbability
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
