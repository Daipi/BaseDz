package com.daipi.base

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * author:daijs
 * time:2021/10/18 15:00
 * details:APP全局线程池管理
 */
class AppExecutors private constructor(diskIO:Executor, networkIO:Executor, mainThread:Executor){
    companion object {
        const val THREAD_COUNT = 3
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder= AppExecutors(DiskIOThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),MainThreadExecutor())
    }

    private var diskIO // 磁盘任务IO
            : Executor? = null

    private var networkIO // 网络任务IO
            : Executor? = null

    private var mainThread // 主线程任务
            : Executor? = null

    init {
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }
    // TODO 真正对外暴漏出去被调用的函数 （磁盘线程池 异步）
    fun diskIO(): Executor? {
        return diskIO
    }

    // TODO 真正对外暴漏出去被调用的函数 （网络线程池 异步）
    fun networkIO(): Executor? {
        return networkIO
    }

    // TODO 真正对外暴漏出去被调用的函数 （主线程 非异步）
    fun mainThread(): Executor? {
        return mainThread
    }

    // 主线程任务
    private class MainThreadExecutor : Executor {
        // 从异步线程 到 UI线程  Looper.getMainLooper()
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    // 磁盘任务IO
    internal class DiskIOThreadExecutor : Executor {
        private val mDiskIO: Executor
        override fun execute(command: Runnable) {
            mDiskIO.execute(command)
        }

        init {
            mDiskIO = Executors.newSingleThreadExecutor()
        }
    }
}