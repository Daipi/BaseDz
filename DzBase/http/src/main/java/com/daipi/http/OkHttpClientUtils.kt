package com.daipi.http

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpClientUtils private constructor(okHttpClient: OkHttpClient?) {
    var okHttpClient: OkHttpClient? = null

    init {
        if (okHttpClient == null) {
            this.okHttpClient = OkHttpClient
                .Builder()
                .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(5, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时时间
                .build()
        } else {
            this.okHttpClient = okHttpClient
        }
    }

    companion object {
        @Volatile
        private var mInstance: OkHttpClientUtils? = null
        fun initClient(okHttpClient: OkHttpClient?): OkHttpClientUtils? {
            if (mInstance == null) {
                synchronized(OkHttpClientUtils::class.java) {
                    if (mInstance == null) {
                        mInstance = OkHttpClientUtils(okHttpClient)
                    }
                }
            }
            return mInstance
        }

        @JvmStatic
        val instance: OkHttpClientUtils?
            get() = initClient(null)
    }

}