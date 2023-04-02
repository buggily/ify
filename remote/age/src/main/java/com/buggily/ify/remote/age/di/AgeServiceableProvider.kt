package com.buggily.ify.remote.age.di

import com.buggily.ify.remote.age.RemoteAgeServiceable
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
        @AgeRetrofitQualifier
        retrofit: Retrofit,
    ): RemoteAgeServiceable = retrofit.create(RemoteAgeServiceable::class.java)
}
