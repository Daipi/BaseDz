package com.daipi.practice.design.behavior

/**
 * author:daijs
 * time:2021/11/3
 * details:迭代器模式练习
 * 迭代器模式提供一种遍历聚合对象中的元素的一种方式，在不暴露底层实现的情况下。
 * 在 Kotlin 下，定义 operator fun iterator() 迭代器函数，或者是作为扩展函数时，可以在 for 循环中遍历。
 */
class Sentence(val words: List<String>)

// operator fun Sentence.iterator(): Iterator<String> = words.iterator()
// or
operator fun Sentence.iterator(): Iterator<String> = object : Iterator<String> {
    val iterator = words.iterator()

    override fun hasNext(): Boolean = iterator.hasNext()

    override fun next(): String = iterator.next()
}