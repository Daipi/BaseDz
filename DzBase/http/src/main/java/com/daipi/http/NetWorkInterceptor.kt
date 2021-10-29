package com.daipi.http

import android.text.TextUtils
import com.daipi.base.utils.LogUtil
import com.daipi.http.server.UrlConstant
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okio.Buffer
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NetWorkInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        val headers = headers
        for (i in 0 until headers.size) {
            builder.addHeader(headers.name(i), headers.value(i))
        }
        request = builder.build()
        //打印request日志
        logForRequest(request)
        val response = chain.proceed(request)
        //打印response日志
        logForResponse(response)
        val date = response.headers["Date"]
        val format: DateFormat =
            SimpleDateFormat("MM月dd日 E", Locale.getDefault())
        //        PreferencesUtil.saveString(PreferencesUtil.KEY_DATE, format.format(new Date(date)));
//        //获取是否带有sessionstatus头标记，如有，则表示会话已过期
//        String status = response.header("sessionstatus");
//        if (status != null) {
//            ClearUtil.clearLoginStatus(BaseUtils.getContext());
//            Intent intent = new Intent(BaseUtils.getContext(), LoginActivity.class);
//            //清空栈
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            BaseUtils.getContext().startActivity(intent);
//            throw new IOException();
//        }
        return response
    }

    private val headers: Headers
        private get() {
            val params =
                HashMap<String, String>()
            val cookies =
                UrlConstant.SERVER?.let {
                    it.toHttpUrlOrNull()?.let {
                        OkHttpClientUtils.instance!!.okHttpClient!!.cookieJar
                            .loadForRequest(it)
                    }
                }
            val buffer = StringBuffer()
            if (cookies != null && cookies.size > 0) {
                for (cook in cookies) {
                    buffer.append(cook.toString()).append(";")
                }
            }
            val result = buffer.toString()
            if (!TextUtils.isEmpty(result)) {
                params["Cookie"] = result
            }
            params["User-Agent"] = "Android"
            params["version"] = BuildConfig.VERSION_NAME
            return params.toHeaders()
        }

    private fun logForResponse(response: Response) {
        LogUtil.d("response's log---------------------------------start")
        LogUtil.d("code: " + response.code)
        LogUtil.d("protocol: " + response.protocol)
        val headers = response.headers
        if (headers != null && headers.size != 0) {
            LogUtil.i(headers.toString())
        }
        try {
            //这里不能直接用response.body().string(),因为调用改方法后流就关闭，程序就可能会发生异常
            //我们需要创建出一个新的ResponseBody给应用层调用
            val body = response.peekBody(1024 * 1024.toLong())
            if (body != null) {
                val result = body.string()
                LogUtil.d("result : $result")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        LogUtil.d("response's log---------------------------------end")
    }

    private fun logForRequest(request: Request) {
        LogUtil.d("request's log----------------------------------start")
        LogUtil.d("url: " + request.url)
        LogUtil.d("method: " + request.method)
        val headers = request.headers
        if (headers != null && headers.size != 0) {
            LogUtil.i("headers: $headers")
        }
        val body = request.body
        if (body != null) {
            val buffer = Buffer()
            try {
                body.writeTo(buffer)
                LogUtil.e("body params : " + buffer.snapshot().utf8())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        LogUtil.d("request's log----------------------------------end")
    }
}