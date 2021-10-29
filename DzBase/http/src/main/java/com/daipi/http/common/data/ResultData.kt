package com.daipi.http.common.data



open class ResultData<T>{
    val code = 0
    val message: String? = null
    val data: T? = null
}
