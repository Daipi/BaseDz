package com.daipi.practice.design.behavior

/**
 * author:daijs
 * time:2021/11/3
 * details:命令模式练习
 * 命令模式是将请求封装为命令对象，解耦请求发送者与接收者，对请求排队或者记录请求日志，以及支持可撤销的操作
 */
interface Command {
    var value: Int
    val param: Int
    fun execute()
    fun undo()
}

class AddCommand(override var value: Int, override val param: Int) : Command{
    override fun execute() {
        value += param
        println("execute add command and value:$value")
    }

    override fun undo() {
        value -= param
        println("undo add command and value:$value")
    }
}

class MultiplyCommand(override var value: Int, override val param: Int) : Command {
    override fun execute() {
        value *= param
        println("execute multiply command and value:$value")
    }

    override fun undo() {
        value /= param
        println("undo multiply command and value:$value")
    }
}

class Calculator {
    val queue = mutableListOf<Command>()

    fun compute(command: Command) {
        command.execute()
        queue.add(command)
    }
    fun undo() {
        queue.lastOrNull()?.undo()
        queue.removeAt(queue.lastIndex)
    }
}