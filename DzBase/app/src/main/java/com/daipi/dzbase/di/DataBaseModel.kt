package com.daipi.dzbase.di

import android.content.Context
import com.daipi.dzbase.room.AppDatabase
import com.daipi.dzbase.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModel {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideGardenPlantingDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}