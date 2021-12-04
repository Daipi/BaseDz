package com.daipi.base

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.daipi.base.utils.LogUtil
import java.util.concurrent.atomic.AtomicBoolean

/**
 * MutableLiveData的一个特性，只要当注册的观察者处于前台时，都会收到通知。那这个特性又影响了什么呢？
 * 我在errorLiveData的监听中，对不同的异常进行了Toast的弹出提醒，如果每次进入一个页面，虽然请求成功了，
 * 但是因为errorLiveData还是能接收到通知，就会弹出一个Toast提醒框
 * 针对MutableLiveData将其修改为单事件响应的liveData，只有一个接收者能接收到信息，可以避免不必要的业务的场景中的事件消费通知
 */
class SingleLiveData<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            LogUtil.d(TAG, "多个观察者存在的时候，只会有一个被通知到数据更新")
        }

        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })

    }

    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveData"
    }
}