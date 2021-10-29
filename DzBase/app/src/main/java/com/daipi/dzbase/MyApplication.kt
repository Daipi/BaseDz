package com.daipi.dzbase

import android.app.Application
import com.daipi.base.utils.ContextUtils
import com.daipi.common.MmkvUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ContextUtils.inject(this)
        MmkvUtil.inject(ContextUtils.context)
        MmkvUtil.getKv()?.encode("daipi","999")
    }
}