package com.buggily.ify.data.di.rest.age

import com.buggily.ify.data.rest.age.AgeServiceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AgeServiceableProvider {

    @Provides
    fun provides(
        @AgeRetrofitQualifier retrofit: Retrofit,
    ): AgeServiceable = retrofit.create(AgeServiceable::class.java)
}
