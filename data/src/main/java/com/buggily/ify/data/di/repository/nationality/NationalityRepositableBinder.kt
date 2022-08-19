package com.buggily.ify.data.di.repository.nationality

import com.buggily.ify.data.repository.nationality.NationalityRepositable
import com.buggily.ify.data.repository.nationality.NationalityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NationalityRepositableBinder {

    @Binds
    fun binds(
        repository: NationalityRepository,
    ): NationalityRepositable
}
