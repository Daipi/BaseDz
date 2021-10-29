package com.daipi.base

import android.app.Activity
import android.text.TextUtils
import java.util.*
import java.util.concurrent.Executors

/**
 * author:daijs
 * time:2021/10/18 15:00
 * details:Activity管理
 */
class AppActivityManager private constructor(){
    companion object {
        var sActivityStack: Stack<Activity>? = null
        @JvmStatic
        fun getInstance():AppActivityManager {
            return SingletonHolder.mInstance
        }
    }

    private object SingletonHolder {
        val mInstance: AppActivityManager = AppActivityManager()
    }

    private fun readResolve(): Any {//防止单例对象在反序列化时重新生成对象
        return SingletonHolder.mInstance
    }

    fun addActivity(activity: Activity?) {
        if (AppActivityManager.sActivityStack == null) AppActivityManager.sActivityStack =
            Stack<Activity>()
        AppActivityManager.sActivityStack!!.add(activity)
    }

    fun removeActivity(activity: Activity?) {
        var activity = activity
        if (sActivityStack != null && activity != null) {
            sActivityStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }

    fun removeActivity(className: String) {
        if (sActivityStack != null && !TextUtils.isEmpty(className)) {
            for (activity in sActivityStack!!) {
                if (activity.javaClass.name == className) {
                    activity.finish()
                }
            }
        }
    }

    fun finishAllActivity() {
        if (sActivityStack != null) {
            while (!sActivityStack!!.isEmpty()) {
                var activity = sActivityStack!!.pop()
                if (!activity!!.isFinishing || !activity.isDestroyed) {
//                    LogUtil.e("----finish---->", activity.getClass().getSimpleName() + " run finish");
                    activity.finish()
                    activity = null
                }
            }
            sActivityStack!!.clear()
        }
    }

    fun getLast(): Activity? {
        return sActivityStack!!.lastElement()
    }

    fun finishAllActivityExcept(vararg className: String): Boolean {
        var flag = false
        if (sActivityStack != null) {
            for (activity in sActivityStack!!) {
                for (str in className) {
                    val cls = activity.javaClass.name
                    if (cls == str) {
                        flag = true
                    } else {
                        activity.finish()
                    }
                }
            }
        }
        return flag
    }
}