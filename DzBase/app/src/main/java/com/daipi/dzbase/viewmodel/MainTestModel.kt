package com.daipi.dzbase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daipi.base.utils.ContextUtils
import com.daipi.base.utils.LogUtil
import com.daipi.http.netApi.NetWorkApi
import com.daipi.dzbase.room.AppDatabase
import com.daipi.dzbase.room.User
import com.daipi.dzbase.room.UserDao
import com.daipi.dzbase.room.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class MainTestModel @Inject constructor (
    private val userRepository: UserRepository
    ): ViewModel() {

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        val job = viewModelScope.launch {
            user.postValue(getDBData()[0])
        }
        repeat(2) { i ->
            LogUtil.d("xxx $i")
        }
        //job.cancel()
    }

    private suspend fun getDBData() = withContext(Dispatchers.IO) {
        //userDao = ContextUtils.context?.let { AppDatabase.getInstance(it) }!!.userDao()
        val data = NetWorkApi.getInstance().project2
        val result: MutableList<User>
        if (data != null) {
            if (userRepository.getUsers().isNotEmpty()) {
                LogUtil.d("xxxxxxxxxxxxxxxxxxxxx"+userRepository.getUsers().size)
                for ((i, e) in data.withIndex()) {
                    userRepository.updateUser(User(i+1, e.name, e.id.toString()))
                }
                userRepository.updateUser(User(1, "MOMO酱", "ppp"))
            } else {
                for (e in data) {
                    userRepository.createUser(User(null, e.name, e.id.toString()))
                }
            }
            result = userRepository.getUsers() as MutableList<User>
        } else {
            result = userRepository.getUsers() as MutableList<User>
            if (result.isEmpty()) {
                delay(1000)
                result.add(User(1, "Jueshen", "Dai"))
            }
        }
        return@withContext result
    }
}