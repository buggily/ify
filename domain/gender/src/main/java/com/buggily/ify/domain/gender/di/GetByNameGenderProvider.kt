package com.buggily.ify.domain.gender.di

import com.buggily.ify.data.gender.GenderRepositable
import com.buggily.ify.domain.gender.GetGenderByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GetByNameGenderProvider {

    @Provides
    fun provides(
        genderRepository: GenderRepositable,
    ): GetGenderByName = GetGenderByName(
        genderRepository = genderRepository,
    )
}
