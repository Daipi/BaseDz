package com.daipi.practice.design.behavior

import android.view.MotionEvent

/**
 * author:daijs
 * time:2021/11/3
 * details:责任链模式练习
 * 职责链模式通过建立一条链来组织请求的处理者，请求将沿着链进行传递，请求发送者无须知道请求在何时、何处以及如何被处理，
 * 实现了请求发送者与处理者的解耦。Kotlin 下的实现与 Java 一样，看下面这个简易的 Android 事件传递的例子，
 * event 不知道是否被 ViewGroup 拦截并处理。
 */
interface EventHandler {
    var next: EventHandler?
    fun handle(event: MotionEvent): Boolean
}

open class View: EventHandler {
    override var next: EventHandler? = null
    override fun handle(event: MotionEvent): Boolean {
        return onTouchEvent()
    }
    open fun onTouchEvent() : Boolean {
        return false
    }
}

open class ViewGroup : View() {
    override fun handle(event: MotionEvent): Boolean {
        return if (onInterceptTouchEvent(event)) onTouchEvent()
        else next?.handle(event)!!
    }

    open fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        //是否拦截
        return false
    }
}