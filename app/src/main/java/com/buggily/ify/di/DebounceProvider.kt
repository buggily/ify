package com.buggily.ify.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Module
@InstallIn(SingletonComponent::class)
object DebounceProvider {

    @Provides
    @DebounceQualifier
    fun provides(): Long = 1000
}
