package com.buggily.ify.core.local.di

import android.content.Context
import androidx.room.Room
import com.buggily.ify.core.local.IfyDatabase
import com.buggily.ify.core.local.IfyDatabaseable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object IfyDatabaseableProvider {

    @Provides
    fun provides(
        @ApplicationContext
        context: Context,
    ): IfyDatabaseable = Room.databaseBuilder(
        context = context,
        klass = IfyDatabase::class.java,
        name = NAME,
    ).build()

    private const val NAME = "ify.db"
}
