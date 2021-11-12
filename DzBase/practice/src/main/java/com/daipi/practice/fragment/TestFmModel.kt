package com.daipi.practice.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daipi.common.MmkvUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
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
    var i = 0
    private val lateText: Flow<Int> = flow {
        while (true) {
            val lateText = i
            emit(lateText)
            delay(5000)
            i++
        }
    }

    val producer: Flow<Int> = flow {
        for (i in 1..10) {
            delay(1000)// 等待一定间隔
            emit(i)
        }
    }
    suspend fun FlowTest3() {
        producer.map { it * 10 }
            .catch { emit(100) }
            .map { it + 1 }
            .collect{ print(it) }
    }
    fun main() = runBlocking {
        flow {
            emit(1)
            try {
                throw RuntimeException()
            } catch (e: Exception) {
                e.stackTrace
            }

        }.onCompletion { println("Done") }
            .collect { println(it) }
    }

    suspend fun firstX(): Int {
        return producer.reduce { accumulator, value ->
            accumulator + value
        }
    }
    suspend fun firstM(): Int {
        return producer.filter { it % 2 == 1 }.first()
    }
    suspend fun FlowTest2():List<Int> {
        return producer.filter { it % 2 == 0 }.toList()
    }
    suspend fun FlowTest() {
        producer.collect { data ->
            print("result: $data")
        }
    }

    val intermediary: Flow<String> = producer.transform {
        emit("transform$it")
    }
/*    val producer2: Flow<String> = flow {
        for (i in 10..20) {
            delay(2000)// 等待一定间隔
            emit("producer2:$i")
        }
    }

    val intermediary: Flow<String> = producer.combine(producer2) {
        i, s -> i.toString() + s
    }*/
    //val intermediary: Flow<Int> = producer.conflate().onEach { delay(2000) }

/*    val intermediary: Flow<Int> = producer
        .filter { it < 5 }
        .map { data -> data * 100 }
        .take(3)*/


    val amendLateText: Flow<Int> = lateText
        .map { text -> text * 100 }
        .onEach { text -> text/100}

    init {
        text.value = MmkvUtil.getKv()?.decodeString("fw_count")
    }

    fun getTextData() {
        text.value = MmkvUtil.getKv()?.decodeString("fw_count")
    }
}