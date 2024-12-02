package com.buggily.ify.core.local.di.gender

import com.buggily.ify.core.local.IfyDatabaseable
import com.buggily.ify.local.gender.LocalGenderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalGenderDaoProvider {

    @Provides
    fun provides(
        ifyDatabase: IfyDatabaseable,
    ): LocalGenderDao = ifyDatabase.getLocalGenderDao()
}
