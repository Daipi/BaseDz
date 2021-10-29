package com.daipi.dzbase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class DogModule {
    @Provides
    fun provideDog() = Dog("ibu")
}