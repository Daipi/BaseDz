package com.daipi.base.autoservice

import java.lang.Exception
import java.util.*

/**
 * author:daijs
 * time:2021/10/20 11:06
 * details:AutoService加载
 */
object BaseServiceLoader {
    fun <S> load(service: Class<S>?): S? {
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (a: Exception) {
            null
        }
    }
}