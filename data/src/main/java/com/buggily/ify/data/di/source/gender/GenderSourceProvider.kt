package com.buggily.ify.data.di.source.gender

import com.buggily.ify.data.rest.gender.GenderServiceable
import com.buggily.ify.data.source.gender.GenderSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GenderSourceProvider {

    @Provides
    fun provides(
        service: GenderServiceable,
    ): GenderSource = GenderSource(
        service = service,
    )
}
