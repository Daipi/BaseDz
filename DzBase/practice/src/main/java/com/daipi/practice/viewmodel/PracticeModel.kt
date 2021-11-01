package com.daipi.practice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daipi.base.utils.LogUtil
import com.daipi.http.netApi.NetWorkApi
import com.daipi.http.netApi.test.ProjectType
import com.daipi.practice.Pig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class PracticeModel @Inject constructor (): ViewModel() {

    val user: MutableLiveData<ProjectType> by lazy {
        MutableLiveData<ProjectType>()
    }

    val pig: MutableLiveData<Pig> by lazy {
        MutableLiveData<Pig>()
    }

    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    init {
        pig.value?.run() {
            setName("8888")
            val job = viewModelScope.launch {
                user.postValue(getDBData()[0])
            }
            repeat(100) { i ->
                LogUtil.d("xxx $i")
            }
            viewModelScope.launch {
                delay(5000)
                setName("9999")
            }
        }

        //job.cancel()
    }

    private suspend fun getDBData() = withContext(Dispatchers.IO) {
        return@withContext NetWorkApi.getInstance().project2
    }
}