package com.buggily.ify.domain.di.use.age

import com.buggily.ify.data.repository.age.AgeRepositable
import com.buggily.ify.domain.use.age.GetAge
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
