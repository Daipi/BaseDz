package com.daipi.practice

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


class Pig : BaseObservable() {
    var name:String = ""
    var age :Int = 0

    @JvmName("getName1")
    @Bindable
    fun getName(): String {
        return name
    }

    @JvmName("setName1")
    fun setName(name: String) {
        this.name = name
        notifyPropertyChanged(BR.name1)
    }

    @JvmName("getAge1")
    @Bindable
    fun getAge(): Int {
        return age
    }

    @JvmName("setAge1")
    fun setAge(age: Int) {
        this.age = age
        notifyPropertyChanged(BR.age1)
    }

}