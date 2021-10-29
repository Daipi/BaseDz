package com.daipi.base.utils

import android.content.Context
import com.daipi.base.utils.ContextUtils.context
import kotlin.jvm.JvmOverloads
import kotlin.Throws
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.os.Build
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * author:daijs
 * time:2021/10/18 17:44
 * details:日志工具类，使用此工具类进行日志统一输出
 */
object LogUtil {
    private const val TAG = "Logger"
    private var isDebug = true
    private val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

    val now = {
        dateFormat.format(Date(System.currentTimeMillis()))
    }

    fun openLog() {
        isDebug = true
    }

    fun e(obj: Any) {
        if (isDebug) {
            Log.e(TAG, obj.toString())
        }
    }
    @JvmStatic
    fun e(tag: String?, obj: Any) {
        if (isDebug) {
            Log.e(tag, obj.toString())
        }
    }

    fun d(obj: Any) {
        if (isDebug) {
            Log.d(TAG, "${now()}[${Thread.currentThread().name}]$obj")
        }
    }

    @JvmStatic
    fun d(tag: String?, obj: Any) {
        if (isDebug) {
            Log.d(tag, obj.toString())
        }
    }

    fun i(obj: Any) {
        if (isDebug) {
            Log.i(TAG, obj.toString())
        }
    }
    @JvmStatic
    fun i(tag: String?, obj: Any) {
        if (isDebug) {
            Log.i(tag, obj.toString())
        }
    }

    @JvmOverloads
    fun writeFile(context: Context?, msg: String?, logType: String = "log") {
        var context = context
        try {
            if (context == null) context = ContextUtils.context
            val writer = getFileWriter(context, logType)
            val pw = PrintWriter(writer)
            pw.print(getDate("yyyy-MM-dd HH:mm:ss") + "   ")
            pw.println(msg)
            pw.flush()
            pw.close()
        } catch (e1: IOException) {
            if (isDebug) {
                e1.printStackTrace()
            }
        }
    }

    fun writeFile(context: Context, t: Throwable) {
        try {
            val writer = getFileWriter(context, "error")
            val pw = PrintWriter(writer)
            pw.println("---------------")
            pw.println(getDate("yyyy-MM-dd HH:mm:ss"))
            savePhoneInfo(context, pw)
            pw.println("---------------")
            t.printStackTrace(pw)
            pw.flush()
            pw.close()
        } catch (e: Exception) {
            if (isDebug) {
                e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    private fun getFileWriter(context: Context?, msgType: String): FileWriter {
        val state = Environment.getExternalStorageState()
        var path = ""
        path = if (Environment.MEDIA_MOUNTED == state) {
            //存在sd卡，则写入sd卡
            (context!!.getExternalFilesDir("")!!.absolutePath + File.separator
                    + "log" + File.separator + msgType + getDate("yyyy-MM-dd") + ".log")
        } else {
            //不存在，则写入到应用内存
            (context!!.filesDir.toString() + File.separator
                    + "log" + File.separator + msgType + getDate("yyyy-MM-dd") + ".log")
        }
        val file = File(path)
        val dir = file.parentFile
        if (!dir.exists()) {
            dir.mkdirs()
        }
        if (!file.exists()) {
            file.createNewFile()
        }
        return FileWriter(file, true)
    }

    private fun getDate(s: String): String {
        val format = SimpleDateFormat(s, Locale.CHINA)
        return format.format(Date())
    }

    @Throws(PackageManager.NameNotFoundException::class)
    private fun savePhoneInfo(context: Context, pw: PrintWriter) {
        //版本信息
        pw.print("版本信息-------")
        val packageManager = context.packageManager
        val packageInfo =
            packageManager.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)
        pw.println(packageInfo.versionName)
        //手机信息
        pw.print("系统版本----")
        pw.print(Build.VERSION.RELEASE)
        pw.print("_")
        pw.println(Build.VERSION.SDK_INT)
        pw.print("手机型号----")
        pw.println(Build.MODEL)
        pw.print("生产厂商----")
        pw.println(Build.MANUFACTURER)
    }
}