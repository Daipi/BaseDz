package com.daipi.base.interceptor

import com.daipi.base.utils.LogUtil
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

/**
 * author:daijs
 * time:2021/10/25 11:44
 * details:协程回调拦截器
 */
class MyContinuationInterceptor() : ContinuationInterceptor {
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation(continuation)

    class MyContinuation<T>(private val continuation: Continuation<T>): Continuation<T> {
        override val context = continuation.context
        override fun resumeWith(result: Result<T>) {
            LogUtil.d("<MyContinuation> $result")
            continuation.resumeWith(result)
        }
    }
}

