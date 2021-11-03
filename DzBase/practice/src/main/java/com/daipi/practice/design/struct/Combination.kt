package com.daipi.practice.design.struct

import java.lang.RuntimeException

/**
 * author:daijs
 * time:2021/11/2
 * details:组合模式练习
 * 组合模式是对树形结构的处理，让调用者忽视单个对象和组合结构的差异，通常会新增包含叶子节点和容器节点接口的抽象构件 Component
 */
interface AbstractFile {
    var childCount: Int
    fun getChild(i: Int): AbstractFile
    fun size(): Long
}

class File(val size: Long,override var childCount: Int): AbstractFile {
    override fun getChild(i: Int): AbstractFile {
        throw RuntimeException("You shouldn't call the method in File")
    }
    override fun size() = size
}

class Folder(override var childCount: Int) : AbstractFile {
    override fun getChild(i: Int): AbstractFile {
        return File(2,2)//凑数代码
    }

    override fun size(): Long {
        return (0..childCount)
            .map { getChild(it).size() }
            .sum()
    }
}