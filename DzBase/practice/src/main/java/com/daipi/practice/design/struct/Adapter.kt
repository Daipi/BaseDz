package com.daipi.practice.design.struct

/**
 * author:daijs
 * time:2021/11/2
 * details:适配器模式练习
 * 适配器模式是把一个不兼容的接口转化为另一个类可以使用的接口
 */
interface Target {
    fun request()
}
interface IAdapter {
    fun ask()
}

class Adapter(val iAdapter: IAdapter): Target {
    override fun request() {
        iAdapter.ask()
    }
}