package com.buggily.ify.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NameProvider {

    @Provides
    @NameQualifier
    fun provides(): String = "Adam"
}
