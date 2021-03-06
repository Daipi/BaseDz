package com.daipi.dzbase


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface MainModule {
    @Binds
    fun bindEngine(chinaEngine: ChinaEngine): Engine
}