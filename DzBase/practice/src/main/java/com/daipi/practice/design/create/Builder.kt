package com.daipi.practice.design.create

import android.app.Dialog
import com.daipi.base.utils.ContextUtils


/**
 * author:daijs
 * time:2021/11/2
 * details:建造者模式练习
 * 建造者模式是为了构建复杂对象的，一般情况下，Kotlin 中使用标准的apply函数就可以了
 * 或者不想公开构造函数，只想通过 Builder 来构建对象，这时可以使用 Type-Safe Builders：
 */

val dialog = ContextUtils.context?.let { Dialog(it).apply {
        setTitle("xx")
        setCancelable(false)
        setCanceledOnTouchOutside(true)
        show()
}
}

class Car(
    val model: String?,
    val year: Int
    ) {
    private constructor(builder: Builder) : this(builder.model, builder.year)

    class Builder {
        var model: String? = null
        var year: Int = -1
        fun build() = Car(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}
val car = Car.build {
    model = "dz"
    year = 27
}