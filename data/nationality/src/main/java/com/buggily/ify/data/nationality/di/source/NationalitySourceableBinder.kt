package com.buggily.ify.data.nationality.di.source

import com.buggily.ify.data.nationality.source.NationalitySource
import com.buggily.ify.data.nationality.source.NationalitySourceable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NationalitySourceableBinder {

    @Binds
    fun binds(
        source: NationalitySource,
    ): NationalitySourceable
}
