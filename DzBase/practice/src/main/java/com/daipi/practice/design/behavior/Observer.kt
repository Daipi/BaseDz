package com.daipi.practice.design.behavior

import kotlin.properties.Delegates

/**
 * author:daijs
 * time:2021/11/3
 * details:观察者模式练习
 * 观察者模式是一个对象状态发生变化后，可以立即通知已订阅的另一个对象
 */
interface TextChangeListener {
    fun onTextChanged(newText: String)
}

class TextView {
    var listener: TextChangeListener? = null

    var text: String by Delegates.observable("") { _, _, new ->
        listener?.onTextChanged(new)
    }
}