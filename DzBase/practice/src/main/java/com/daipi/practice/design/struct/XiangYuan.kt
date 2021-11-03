package com.daipi.practice.design.struct

import java.util.*

/**
 * author:daijs
 * time:2021/11/3
 * details:享元模式练习
 * 享元模式以共享的方式高效地支持大量细粒度对象的重用，享元对象能做到共享的关键是区分了可共享内部状态和不可共享外部状态
 */
enum class XYColor {
    black, white
}

open class Chess(val color: XYColor) {
    fun display(pos: Pair<Int, Int>) {
        println("The $color chess at position $pos")
    }
}

class BlackChess : Chess(color = XYColor.black)
class WhiteChess : Chess(color = XYColor.white)

object ChessFactory {
    private val table = Hashtable<XYColor, Chess>()

    init {
        table[XYColor.black] = BlackChess()
        table[XYColor.white] = WhiteChess()
    }

    fun getChess(color: XYColor) = table[color]!!
}

fun Test() {
    val blackChess = ChessFactory.getChess(XYColor.black)
    val whiteChess = ChessFactory.getChess(XYColor.white)
    blackChess.display(Pair(9, 5))
    whiteChess.display(Pair(2, 5))
    whiteChess.display(Pair(6, 8))
}