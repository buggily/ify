package com.buggily.ify.data.gender.di.source

import com.buggily.ify.data.gender.source.GenderSource
import com.buggily.ify.remote.gender.GenderServiceable
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
