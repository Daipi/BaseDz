package com.daipi.practice.design.struct

/**
 * author:daijs
 * time:2021/11/2
 * details:外观模式练习
 * 外观模式是为一个复杂的子系统提供一个简化的统一接口
 */
class ComplexSystemStore(val filePath: String) {
    init {
        println("Reading data from file: $filePath")
    }

    val store = HashMap<String, String>()

    fun store(key: String, payload: String) {
        store.put(key, payload)
    }

    fun read(key: String): String = store[key] ?: ""

    fun commit() = println("Storing cached data: $store to file: $filePath")
}

data class User(val nikeName: String)

//Facade:
class UserRepository {
    val systemPreference = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreference.store("UserKey", user.nikeName)
        systemPreference.commit()
    }

    fun findFirst(): User = User(systemPreference.read("UserKey"))
}

// usage
fun test(){
    val userRepository = UserRepository()
    val user = User("daipi")
    userRepository.save(user)
    val result = userRepository.findFirst()
}


