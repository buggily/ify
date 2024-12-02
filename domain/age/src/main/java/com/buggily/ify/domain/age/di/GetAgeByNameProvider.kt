package com.buggily.ify.domain.age.di

import com.buggily.ify.core.domain.FormatNumber
import com.buggily.ify.data.age.AgeRepositable
import com.buggily.ify.domain.age.GetAgeByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object GetAgeByNameProvider {

    @Provides
    fun provides(
        ageRepository: AgeRepositable,
        formatNumber: FormatNumber,
    ): GetAgeByName = GetAgeByName(
        ageRepository = ageRepository,
        formatNumber = formatNumber,
    )
}
