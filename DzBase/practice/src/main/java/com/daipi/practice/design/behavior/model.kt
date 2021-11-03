package com.daipi.practice.design.behavior

/**
 * author:daijs
 * time:2021/11/3
 * details:模板方法模式练习
 * 模板方法模式提供了一个模板方法来定义算法框架，而某些具体步骤的实现可以在其子类中完成，Kotlin 中使用高阶函数可以避免继承的方式
 */
class Task {
    fun run(step2: () -> Unit, step3: () -> Unit) {
        step1()
        step2()
        step3()
    }

    fun step1(){}
}