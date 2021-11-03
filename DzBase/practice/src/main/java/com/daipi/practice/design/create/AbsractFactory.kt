package com.daipi.practice.design.create

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView


/**
 * author:daijs
 * time:2021/11/2
 * details:抽象工厂模式练习
 * 工厂方法针对一种产品，而抽象工厂是针对一系列产品，为每种产品定义一个工厂方法，工厂子类负责创建该系列的多种产品
 */
class SeriesATextView(context: Context) : AppCompatTextView(context)
class SeriesBTextView(context: Context) : AppCompatTextView(context)

class SeriesAButton(context: Context) : AppCompatButton(context)
class SeriesBButton(context: Context) : AppCompatButton(context)

interface AbstractFactory {
    val context: Context
    fun makeTextView(): AppCompatTextView
    fun makeButton(): AppCompatButton
}

class SeriesAFactory(override val context: Context) : AbstractFactory {
    override fun makeTextView() = SeriesATextView(context)
    override fun makeButton() = SeriesAButton(context)
}

class SeriesBFactory(override val context: Context) : AbstractFactory {
    override fun makeTextView() = SeriesBTextView(context)
    override fun makeButton() = SeriesBButton(context)
}