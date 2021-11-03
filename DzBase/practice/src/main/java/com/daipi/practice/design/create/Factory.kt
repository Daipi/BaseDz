package com.daipi.practice.design.create

/**
 * author:daijs
 * time:2021/11/2
 * details:工厂设计模式练习
 * 工厂方法把创建对象的过程抽象为接口，由工厂的子类决定对象的创建
 */
interface Product {
    val name: String
}

class ProductA(override val name: String = "ProductA"): Product
class ProductB(override val name: String = "ProductB"): Product

interface Factory {
    fun makeProduct(): Product
}

class FactoryA: Factory {
    override fun makeProduct() = ProductA()
}
class FactoryB: Factory {
    override fun makeProduct() = ProductB()
}


