package com.buggily.ify.di.use

import com.buggily.ify.use.Lowercase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class LowercaseProvider {

    @Provides
    fun provides(
        locale: Locale,
    ): Lowercase = Lowercase(
        locale = locale,
    )
}
