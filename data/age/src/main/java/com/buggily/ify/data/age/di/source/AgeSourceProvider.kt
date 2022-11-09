package com.buggily.ify.data.age.di.source

import com.buggily.ify.data.age.source.AgeSource
import com.buggily.ify.remote.age.AgeServiceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AgeSourceProvider {

    @Provides
    fun provides(
        service: AgeServiceable,
    ): AgeSource = AgeSource(
        service = service,
    )
}
