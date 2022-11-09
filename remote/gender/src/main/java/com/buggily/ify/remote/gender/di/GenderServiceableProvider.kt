package com.buggily.ify.remote.gender.di

import com.buggily.ify.remote.gender.GenderServiceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GenderServiceableProvider {

    @Provides
    fun provides(
        @GenderRetrofitQualifier retrofit: Retrofit,
    ): GenderServiceable = retrofit.create(GenderServiceable::class.java)
}
