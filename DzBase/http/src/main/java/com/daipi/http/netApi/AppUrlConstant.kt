package com.daipi.http.netApi

import com.daipi.http.server.UrlConstant

object AppUrlConstant {
    @JvmField
    val URL: String = UrlConstant.SERVER + ""

    const val GET_PROJECT = "/project/tree/json"
}