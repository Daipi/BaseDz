package com.daipi.practice.design.create

/**
 * author:daijs
 * time:2021/11/2
 * details:原型设计模式练习
 * 原型模式是以一个对象为原型，创建出一个新的对象，在 Kotlin 下很容易实现，因为使用 data class 时，
 * 会自动获得equals、hashCode、toString和copy方法，而copy方法可以克隆整个对象并且允许修改新对象某些属性。
 */
data class EMail(var recipient: String, var subject: String?, var message: String?)

val mail = EMail("abc@example.com", "Hello", "Don't know what to write.")
val copy = mail.copy(recipient = "other@example.com")