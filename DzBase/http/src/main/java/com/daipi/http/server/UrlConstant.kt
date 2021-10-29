package com.daipi.http.server

/**
 * Author :  ZhengYi
 * Date    :  2020/7/9  10:40
 * QQ  :  501047321
 */
object UrlConstant {
    @JvmField
    var SERVER:String? = ""
    init {
        SERVER = ServerManager.getUrl(ServerUrlImpl)
    }

}