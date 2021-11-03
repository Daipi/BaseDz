package com.daipi.practice.design.struct

/**
 * author:daijs
 * time:2021/11/3
 * details:代理模式练习
 * 代理模式是使用一个代理对象来访问目标对象的行为
 */
interface DLFile {
    fun read(name: String)
}

class NormalFile : DLFile {
    override fun read(name: String) = println("Reading file: $name")
}

// proxy
class SecuredFile : DLFile {
    val normalFile = NormalFile()
    var password = ""

    override fun read(name: String) {
        if (password == "secret") {
            println("Password is correct: $password")
            normalFile.read(name)
        } else {
            println("Incorrect password. Access denied!")
        }
    }
}

