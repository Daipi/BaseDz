package com.daipi.practice.design.struct

/**
 * author:daijs
 * time:2021/11/2
 * details:装饰模式练习
 * 装饰模式可以给一个对象添加额外的行为，在 Kotlin 中可以通过扩展函数简单的实现
 */
class Text(val text: String) {
    fun draw() = print(text)
}

fun Text.newDrawLine(decorated: Text.() -> Unit) {
    print("_")
    this.decorated()
    print("+")
}

class DXX(){
    fun Test() {
        //usage
        Text("Hello").run {
            newDrawLine {
                draw()
            }
        }
    }
}

