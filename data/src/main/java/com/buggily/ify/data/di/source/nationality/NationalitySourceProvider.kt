package com.buggily.ify.data.di.source.nationality

import com.buggily.ify.data.rest.nationality.NationalityServiceable
import com.buggily.ify.data.source.nationality.NationalitySource
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
