package com.daipi.practice.design.behavior

/**
 * author:daijs
 * time:2021/11/3
 * details:备忘录模式练习
 * 备忘录模式是在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态。
 */
data class Memento(val fileName: String, val content: StringBuilder)

class FileWriter(var fileName: String) {
    private var content = StringBuilder()

    fun write(str: String) {
        content.append(str)
    }

    fun save() = Memento(fileName, StringBuilder(content))

    fun restore(m: Memento) {
        fileName = m.fileName
        content = m.content
    }
}