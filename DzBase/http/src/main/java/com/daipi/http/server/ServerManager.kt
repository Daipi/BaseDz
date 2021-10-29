package com.daipi.http.server

import com.daipi.http.BuildConfig

/**
 * Author :  ZhengYi
 * Date    :  2020/7/9  10:42
 * QQ  :  501047321
 */
object ServerManager {
    fun getUrl(impl: IServerUrl): String {
        if (isDebug())
            return impl.debug()
        return impl.release()
    }

    private fun isDebug(): Boolean {
        return BuildConfig.BUILD_TYPE == "debug"
    }
}