package com.buggily.ify.data.di.rest.nationality

import com.buggily.ify.data.rest.nationality.NationalityServiceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NationalityServiceableProvider {

    @Provides
    fun provides(
        @NationalityRetrofitQualifier retrofit: Retrofit,
    ): NationalityServiceable = retrofit.create(NationalityServiceable::class.java)
}
