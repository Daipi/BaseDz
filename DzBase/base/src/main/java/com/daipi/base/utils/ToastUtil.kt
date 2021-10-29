package com.daipi.base.utils

import android.content.Context
import android.widget.Toast
import com.daipi.base.utils.ToastUtil
import android.view.LayoutInflater
import com.daipi.base.R
import android.widget.TextView
import android.view.Gravity

/**
 * author:daijs
 * time:2021/10/18 15:20
 * details:Toast显示（底部，中间）
 */
object ToastUtil {
    private var mToast: Toast? = null
    private var oldMsg: String? = null
    private var time: Long = 0
    fun show(msg: String) {
        show(ContextUtils.context, msg)
    }

    fun show(context: Context?, msg: String) {
        val toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(R.layout.toast_view_center, null)
        (view as TextView).text = msg
        toast.view = view
        toast.duration = Toast.LENGTH_SHORT
        if (msg != oldMsg) { // 当显示的内容不一样时，即断定为不是同一个Toast
            toast.show()
            time = System.currentTimeMillis()
        } else {
            // 显示内容一样时，只有间隔时间大于2秒时才显示
            if (System.currentTimeMillis() - time > 2000) {
                toast.show()
                time = System.currentTimeMillis()
            }
        }
        oldMsg = msg
    }

    fun showCenterToast(msg: String) {
        showCenterToast(ContextUtils.context, msg)
    }

    fun showCenterToast(context: Context?, msg: String) {
        val toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(R.layout.toast_view_center, null)
        (view as TextView).text = msg
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        if (msg != oldMsg) { // 当显示的内容不一样时，即断定为不是同一个Toast
            toast.show()
            time = System.currentTimeMillis()
        } else {
            // 显示内容一样时，只有间隔时间大于2秒时才显示
            if (System.currentTimeMillis() - time > 2000) {
                toast.show()
                time = System.currentTimeMillis()
            }
        }
        oldMsg = msg
    }

    fun getShortToast(hint: String?) {
        if (mToast == null) {
            mToast = Toast.makeText(ContextUtils.context, hint, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(hint)
            mToast!!.duration = Toast.LENGTH_SHORT
        }
        mToast!!.show()
    }
}