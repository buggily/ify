package com.buggily.ify.domain.di.use.gender

import com.buggily.ify.data.repository.gender.GenderRepositable
import com.buggily.ify.domain.use.gender.GetGender
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
