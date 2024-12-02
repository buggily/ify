package com.buggily.ify.core.local.di.age

import com.buggily.ify.core.local.IfyDatabaseable
import com.buggily.ify.local.age.LocalAgeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalAgeDaoProvider {

    @Provides
    fun provides(
        ifyDatabase: IfyDatabaseable,
    ): LocalAgeDao = ifyDatabase.getLocalAgeDao()
}
