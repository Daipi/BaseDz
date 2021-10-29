package com.daipi.common

import android.content.Context
import android.util.Log
import com.daipi.base.utils.ContextUtils
import com.tencent.mmkv.MMKV
import java.lang.NullPointerException

/**
 * author:daijs
 * time:2021/10/19 14:47
 * details:
 */
object MmkvUtil {
    private var kv: MMKV? = null
    fun inject(context: Context?) {
        if (kv == null) {
            val rootDir = MMKV.initialize(context)
            kv = MMKV.defaultMMKV()
        } else {
            Log.e("ContextUtils", "the context has been inject.")
        }
    }

    fun getKv(): MMKV? {
        if (kv == null) {
            throw NullPointerException("the context need init")
        }
        return kv
    }
}