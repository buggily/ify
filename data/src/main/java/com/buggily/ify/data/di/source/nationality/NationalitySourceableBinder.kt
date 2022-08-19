package com.buggily.ify.data.di.source.nationality

import com.buggily.ify.data.source.nationality.NationalitySource
import com.buggily.ify.data.source.nationality.NationalitySourceable
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
