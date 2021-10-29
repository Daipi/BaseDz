package com.daipi.base.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.app.ActivityManager.RunningAppProcessInfo
import android.text.TextUtils
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log
import java.lang.NullPointerException

/**
 * author:daijs
 * time:2021/10/18 15:20
 * details:
 */
@SuppressLint("StaticFieldLeak")
object ContextUtils {
    private var sContext: Context? = null

    /**
     * 全局context
     *
     * @param context
     */
    fun inject(context: Context) {
        if (sContext == null) {
            sContext = context.applicationContext
        } else {
            Log.e("ContextUtils", "the context has been inject.")
        }
    }

    val context: Context?
        get() {
            if (sContext == null) {
                throw NullPointerException("the context need init")
            }
            return sContext
        }

    /**
     * 检测某ActivityUpdate是否在当前Task的栈顶
     */
    fun isTopActivity(context: Context, cmdName: String): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfos = manager.getRunningTasks(1000)
        var cmpNameTemp: String? = null
        //        Log.e("cmdName", cmdName);
        if (null != runningTaskInfos) {
            cmpNameTemp = runningTaskInfos[0].topActivity!!.className
            //            Log.e("cmpNameTemp", cmpNameTemp);
        }
        return if (null == cmpNameTemp) false else cmpNameTemp == cmdName
    }

    fun isRunning(context: Context, packageName: String): Boolean {
        var isRunning = false
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfos = manager.getRunningTasks(1000)
        for (taskInfo in runningTaskInfos) {
            if (taskInfo.baseActivity!!.packageName == packageName) {
                isRunning = true
                break
            }
        }
        return isRunning
    }

    fun isServiceRunning(context: Context, className: String): Boolean {
        var isRunning = false
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = activityManager.getRunningServices(200)
        if (services == null || services.size <= 0) return false
        for (i in services.indices) {
            if (services[i].service.className == className) {
                isRunning = true
                break
            }
        }
        return isRunning
    }

    fun isFrontRunning(context: Context): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = manager.runningAppProcesses ?: return false
        for (info in appProcesses) {
            if (info.processName == context.packageName) {
                return info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

    fun getCurProcessName(context: Context): String? {
        val pid = Process.myPid()
        val activityManager = context
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processes = activityManager.runningAppProcesses ?: return null
        for (appProcess in processes) {
            if (appProcess.pid == pid) {
                return appProcess.processName
            }
        }
        return null
    }

    fun getAppMetaData(context: Context?, key: String?): String? {
        if (context == null || TextUtils.isEmpty(key)) {
            return null
        }
        var channelNumber: String? = null
        try {
            val packageManager = context.packageManager
            if (packageManager != null) {
                val applicationInfo = packageManager.getApplicationInfo(
                    context.packageName,
                    PackageManager.GET_META_DATA
                )
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key)
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return channelNumber
    }

    fun isTaskRoot(activity: Activity): Boolean {
        if (!activity.isTaskRoot) {
            val intent = activity.intent
            val action = intent.action
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == action) {
                activity.finish()
                return false
            }
        }
        return true
    }
}