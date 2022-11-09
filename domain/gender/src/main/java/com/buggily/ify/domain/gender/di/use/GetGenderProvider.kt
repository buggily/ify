package com.buggily.ify.domain.gender.di.use

import com.buggily.ify.data.gender.repository.GenderRepositable
import com.buggily.ify.domain.gender.use.GetGender
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GetGenderProvider {

    @Provides
    fun provides(
        repository: GenderRepositable,
    ): GetGender = GetGender(
        repository = repository,
    )
}
