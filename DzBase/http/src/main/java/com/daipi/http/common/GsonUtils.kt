package com.daipi.http.common

import com.daipi.http.common.data.adapter.DoubleDefaultAdapter
import com.daipi.http.common.data.adapter.FloatDefaultAdapter
import com.daipi.http.common.data.adapter.IntegerDefaultAdapter
import com.daipi.http.common.data.adapter.LongDefaultAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtils {
    private var sGson: Gson? = null

    /**
     * 默认gson
     * @return
     */
    fun gson(): Gson? {
        if (sGson == null) {
            sGson = builder()
                .create()
        }
        return sGson
    }

    fun builder(): GsonBuilder {
        return GsonBuilder()
            .registerTypeAdapter(Double::class.java, DoubleDefaultAdapter())
            .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleDefaultAdapter())
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerDefaultAdapter())
            .registerTypeAdapter(Int::class.java, IntegerDefaultAdapter())
            .registerTypeAdapter(Float::class.javaPrimitiveType, FloatDefaultAdapter())
            .registerTypeAdapter(Float::class.java, FloatDefaultAdapter())
            .registerTypeAdapter(Long::class.javaPrimitiveType, LongDefaultAdapter())
            .registerTypeAdapter(Long::class.java, LongDefaultAdapter())
    }
}