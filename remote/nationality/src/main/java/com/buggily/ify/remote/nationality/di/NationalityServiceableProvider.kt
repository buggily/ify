package com.buggily.ify.remote.nationality.di

import com.buggily.ify.remote.nationality.RemoteNationalityServiceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object NationalityServiceableProvider {

    @Provides
    fun provides(
        @NationalityRetrofitQualifier
        retrofit: Retrofit,
    ): RemoteNationalityServiceable = retrofit.create(RemoteNationalityServiceable::class.java)
}
