package com.daipi.dzbase

import javax.inject.Inject

interface Engine {
    fun on()
    fun off()
}
class ChinaEngine @Inject constructor(): Engine {
    override fun on() {
        TODO("Not yet implemented")
    }

    override fun off() {
        TODO("Not yet implemented")
    }
}
class ChinaCar @Inject constructor(val engine: Engine) {
    lateinit var name: String
}