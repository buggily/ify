package com.buggily.ify.data.nationality.di.source

import com.buggily.ify.data.nationality.source.NationalitySource
import com.buggily.ify.remote.nationality.NationalityServiceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NationalitySourceProvider {

    @Provides
    fun provides(
        service: NationalityServiceable,
    ): NationalitySource = NationalitySource(
        service = service,
    )
}
