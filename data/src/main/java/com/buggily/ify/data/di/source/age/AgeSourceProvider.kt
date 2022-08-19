package com.buggily.ify.data.di.source.age

import com.buggily.ify.data.rest.age.AgeServiceable
import com.buggily.ify.data.source.age.AgeSource
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
