package com.buggily.ify.data.nationality.di.repository

import com.buggily.ify.data.nationality.repository.NationalityRepositable
import com.buggily.ify.data.nationality.repository.NationalityRepository
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
