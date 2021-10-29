package com.daipi.common

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
data class Cat(val name: String, val age: Int) {
    @Inject constructor():this("momo", 27)
}
