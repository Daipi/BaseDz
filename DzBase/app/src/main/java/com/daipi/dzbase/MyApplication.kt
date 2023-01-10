package com.daipi.dzbase

import android.app.Application
import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import com.daipi.anrcheck.AnrError
import com.daipi.anrcheck.AnrInterceptor
import com.daipi.anrcheck.AnrListener
import com.daipi.anrcheck.AnrMonitor
import com.daipi.base.utils.ContextUtils
import com.daipi.common.MmkvUtil
import dagger.hilt.android.HiltAndroidApp
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

@HiltAndroidApp
class MyApplication: Application() {
    var duration = 4L
    val anrMonitor = AnrMonitor(3000)
    override fun onCreate() {
        super.onCreate()
        ContextUtils.inject(this)
        MmkvUtil.inject(ContextUtils.context)
        MmkvUtil.getKv()?.encode("daipi","999")
        anrMonitor.setIgnoreDebugger(true)
            .setReportAllThreads()
            .setAnrListener(object : AnrListener {
                override fun onAppNotResponding(error: AnrError) {
                    Log.i("tag","onAppNotResponding")
                    try {
                        ObjectOutputStream(ByteArrayOutputStream()).writeObject(error)
                    } catch (e: IOException) {
                        throw RuntimeException(e)
                    }
                    Log.i("tag","Anr Error was successfully serialized")
                }
            }).setAnrInterceptor(object : AnrInterceptor {
                override fun intercept(duration: Long): Long {
                    val ret = this@MyApplication.duration - duration
                    if (ret > 0) {
                        Log.i("tag",
                            "Intercepted ANR that is too short ($duration ms), postponing for $ret ms."
                        )
                    }
                    return ret
                }
            })
        ProcessLifecycleOwner.get().lifecycle.addObserver(anrMonitor)
    }

    override fun onTerminate() {
        super.onTerminate()
        anrMonitor.onAppTerminate();
    }
}