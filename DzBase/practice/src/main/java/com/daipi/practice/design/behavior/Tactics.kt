package com.daipi.practice.design.behavior

/**
 * author:daijs
 * time:2021/11/3
 * details:策略模式练习
 * 策略模式用于算法的自由切换和扩展，分离算法的定义与实现，在 Kotlin 中可以使用高阶函数作为算法的抽象
 */
class Customer(val name: String, val fee: Double, val discount: (Double) -> Double) {
    fun pricePreMonth() = discount(fee)
}

// usage
val studentDiscount = { fee:Double -> fee/2 }
val noDiscount = { fee:Double -> fee }

val student = Customer("dzz", 50.0, studentDiscount)
val regular = Customer("momo", 50.0, noDiscount)
