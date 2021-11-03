package com.daipi.practice.design.behavior

/**
 * author:daijs
 * time:2021/11/3
 * details:状态模式练习
 * 状态模式将一个对象在不同状态下的不同行为封装在一个个状态类中，通过设置不同的状态可以让对象拥有不同的行为。
 */
sealed class UserState(val name: String, val isAuthorized: Boolean) {
    abstract fun click()

    class Unauthorized : UserState(name = "Unauthorized", isAuthorized = false) {
        override fun click() {
            print("User is unauthorized.")
        }
    }

    class Authorized(name: String) : UserState(name, isAuthorized = true) {
        override fun click() {
            print("User is authorized as $name")
        }
    }
}

class StUser {
    private var state: UserState = UserState.Unauthorized()

    val name: String
        get() = state.name

    val isAuthorized: Boolean
        get() = state.isAuthorized

    fun click() = state.click()

    fun login(name: String) {
        state = UserState.Authorized(name)
    }
    fun logout() {
        state = UserState.Unauthorized()
    }
}