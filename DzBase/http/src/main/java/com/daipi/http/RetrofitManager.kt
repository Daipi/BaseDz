package com.daipi.http

import com.daipi.http.common.GsonUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import com.daipi.http.server.UrlConstant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author :  ZhengYi
 * Date    :  2020/7/9  10:58
 * QQ  :  501047321
 */
object RetrofitManager {
    private var retrofit:Retrofit? = null
    fun get():Retrofit{
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        retrofit = retrofit ?: Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonUtils.gson()))
            //添加对 Deferred 的支持
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(UrlConstant.SERVER!!)
            .build()
        return retrofit!!
    }
}