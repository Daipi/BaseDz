package com.daipi.practice.design.xingwei

/**
 * author:daijs
 * time:2021/11/3
 * details:访问者模式练习
 * 访问者模式提供一个作用于某对象结构中的各元素的操作表示，它使我们可以在不改变各元素的类的前提下定义作用于这些元素的新操作
 */
interface Employee {
    fun accept(visitor: Visitor)
}

class GeneralEmployee(val wage: Int) : Employee {
    override fun accept(visitor: Visitor) = visitor.visit(this)
}

class ManagerEmployee(val wage: Int, val bonus: Int) : Employee {
    override fun accept(visitor: Visitor) = visitor.visit(this)
}

interface Visitor {
    fun visit(ge: GeneralEmployee)
    fun visit(me: ManagerEmployee)
}

class FADVisitor : Visitor {
    override fun visit(ge: GeneralEmployee) {
        println("GeneralEmployee wage:${ge.wage}")
    }

    override fun visit(me: ManagerEmployee) {
        println("ManagerEmployee wage:${me.wage + me.bonus}")
    }

}