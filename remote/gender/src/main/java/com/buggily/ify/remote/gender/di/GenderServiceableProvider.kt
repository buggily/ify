package com.buggily.ify.remote.gender.di

import com.buggily.ify.remote.gender.RemoteGenderServiceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object GenderServiceableProvider {

    @Provides
    fun provides(
        @GenderRetrofitQualifier
        retrofit: Retrofit,
    ): RemoteGenderServiceable = retrofit.create(RemoteGenderServiceable::class.java)
}
