package com.daipi.http

import com.daipi.http.common.BaseMsgCode
import com.daipi.http.common.BaseResponse
import com.daipi.http.common.ErrorMsg
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Author :  ZhengYi
 * Date    :  2020/7/15  19:15
 * QQ  :  501047321
 */
abstract class ResponseCallback<T> : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        onError(null, ErrorMsg(1, t.message))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        try {
            onResponse(response)
        } catch (e: Exception) {
            onError(response.body(), ErrorMsg(response.code(), e.message))
        }
    }

    @Throws(Exception::class)
    open fun onResponse(response: Response<T>) {
        if (response.isSuccessful) {
            val data = response.body()
            if (data is BaseResponse) {
                if (BaseMsgCode.LOGIN_TIME_OUT == data.code) {
                    throw Exception("登陆失效")
                } else if (BaseMsgCode.CODE_OK == data.code) {
                    onSuccess(data)
                } else {
                    onError(data, ErrorMsg(data.code, data.msg))
                }
            } else if (data is ResponseBody) {
                onBody(response)
            }else {
                onError(data, ErrorMsg(response.code(), response.message()))
            }
        } else {
            onError(response.body(), ErrorMsg(response.code(), response.message()))
        }
    }

    open fun onBody(response: Response<T>){}
    abstract fun onError(response: T?, msg: ErrorMsg)
    abstract fun onSuccess(response: T)
}

