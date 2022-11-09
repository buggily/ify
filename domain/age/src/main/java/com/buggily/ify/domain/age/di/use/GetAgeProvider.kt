package com.buggily.ify.domain.age.di.use

import com.buggily.ify.data.age.repository.AgeRepositable
import com.buggily.ify.domain.age.use.GetAge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GetAgeProvider {

    @Provides
    fun provides(
        repository: AgeRepositable,
    ): GetAge = GetAge(
        repository = repository,
    )
}
