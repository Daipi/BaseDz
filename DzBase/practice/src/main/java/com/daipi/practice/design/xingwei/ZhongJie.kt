package com.daipi.practice.design.xingwei

/**
 * author:daijs
 * time:2021/11/3
 * details:中介者模式练习
 * 中介者模式用一个中介对象（中介者）来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，
 * 从而使其耦合松散，而且可以独立地改变它们之间的交互
 */
interface ChatMediator {
    fun senMsg(msg: String, user: User)
    fun addUser(user: User)
}

abstract class User(val name: String, val mediator: ChatMediator) {
    abstract fun send(msg: String)
    abstract fun receive(msg: String)
}

class ChatMediatorImpl : ChatMediator {
    private val users = mutableListOf<User>()

    override fun senMsg(msg: String, user: User) {
        users.filter { it != user }.forEach { it.receive(msg) }
    }

    override fun addUser(user: User) {
        users.add(user)
    }
}

class UserImpl(name: String, mediator: ChatMediator) : User(name, mediator) {
    override fun send(msg: String) {
        println("$name : sending messages = $msg")
        mediator.senMsg(msg, this)
    }

    override fun receive(msg: String) {
        println("$name : received messages = $msg")
    }
}