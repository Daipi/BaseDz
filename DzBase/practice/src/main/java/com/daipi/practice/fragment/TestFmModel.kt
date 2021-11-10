package com.daipi.practice.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daipi.common.MmkvUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */
@HiltViewModel
class TestFmModel @Inject constructor () : ViewModel() {
    val text: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        text.value = MmkvUtil.getKv()?.decodeString("fw_count")
    }

    fun getTextData() {
        text.value = MmkvUtil.getKv()?.decodeString("fw_count")
    }
}