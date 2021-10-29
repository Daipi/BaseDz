package com.daipi.http.server

/**
 * Author :  ZhengYi
 * Date    :  2020/7/9  10:39
 * QQ  :  501047321
 */
object ServerUrlImpl: IServerUrl {
    override fun debug(): String {
        return "https://www.wanandroid.com"
    }

    override fun release(): String {
        return ""
    }
}