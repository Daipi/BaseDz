package com.daipi.practice.design.struct

/**
 * author:daijs
 * time:2021/11/2
 * details:桥接模式可以分离某个类存在两个独立变化的纬度，把多层继承结构改为两个独立的继承结构，在两个抽象层中有一个抽象关联
 * 接口中定义另一个接口
 */
interface Color {
    fun coloring()
}

class RedColor: Color {
    override fun coloring() {

    }
}
class GreenColor: Color {
    override fun coloring() {

    }
}

interface Pen {
    val colorImpl: Color
    fun write()
}

class BigPen(override val colorImpl: Color): Pen {
    override fun write() {

    }
}
class SmallPen(override val colorImpl: Color): Pen {
    override fun write() {

    }
}

class Ddd {
    val bigPen = BigPen(GreenColor())
}