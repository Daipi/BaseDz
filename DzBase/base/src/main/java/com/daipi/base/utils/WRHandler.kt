package com.daipi.base.utils

import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference

/**
 * author:daijs
 * time:2021/10/18 16:05
 * details:重写的Handler，解决内存泄漏问题
 */
class WRHandler(handlerCallBack: HandlerCallBack) : Handler() {
    private val tWeakReference: WeakReference<HandlerCallBack> = WeakReference(handlerCallBack)
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val handlerCallBack = tWeakReference.get()
        handlerCallBack?.handleMessage(msg)
    }

    interface HandlerCallBack {
        fun handleMessage(msg: Message?)
    }

}