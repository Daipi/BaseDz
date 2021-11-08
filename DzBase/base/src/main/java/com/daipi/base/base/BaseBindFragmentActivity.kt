package com.daipi.base.base

import android.app.Activity
import android.content.ComponentCallbacks2
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.daipi.base.AppActivityManager
import com.daipi.base.R
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * author:daijs
 * time:2021/10/18 15:20
 * details:DataBinding初始化，权限请求，协程作用域取消
 */
abstract class BaseBindFragmentActivity : FragmentActivity(), CoroutineScope by MainScope(), ComponentCallbacks2 {
    val TAG = this.javaClass.simpleName
    protected var mActivity: Activity? = null
    private var dataBinding: ViewDataBinding? = null
    open fun <Bind : ViewDataBinding?> getDataBinding(): Bind {
        return dataBinding as Bind
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        AppActivityManager.getInstance().addActivity(this)
        val resId: Int = getContentViewId()
        if (resId != 0) {
            dataBinding = DataBindingUtil.setContentView(this, getContentViewId())
        }
        overridePendingTransition(R.anim.slide_right_activity, R.anim.slide_left_exit_activity)
    }

    open fun requestWithPermissions(vararg permission: String) {
        AndPermission
            .with(mActivity!!)
            .permission(*permission)
            .rationale { requestCode, rationale -> rationale.resume() }
            .callback(object : PermissionListener {
                override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
                    if (AndPermission.hasPermission(mActivity!!, grantPermissions)) {
                        grantPermission(requestCode, grantPermissions)
                    } else {
                        onFailed(requestCode, grantPermissions)
                    }
                }

                override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
                    deniedPermission(requestCode, deniedPermissions)
                }
            })
            .start()
    }

    open fun deniedPermission(requestCode: Int, deniedPermissions: List<String>?) {}

    open fun grantPermission(requestCode: Int, grantPermissions: List<String>?) {}

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_left_activity, R.anim.slide_right_exit_activity)
    }

    override fun onDestroy() {
        if (dataBinding != null) dataBinding!!.unbind()
        cancel()//取消协程
        AppActivityManager.getInstance().removeActivity(this)
        super.onDestroy()
    }

    /**
     * @return id
     */
    abstract fun getContentViewId(): Int

    //内存状态变化处理
    override fun onTrimMemory(level: Int) {
        // Determine which lifecycle or system event was raised.
        when (level) {

            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
                /*
                   Release any UI objects that currently hold memory.

                   The user interface has moved to the background.
                */
            }

            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {
                /*
                   Release any memory that your app doesn't need to run.

                   The device is running low on memory while the app is running.
                   The event raised indicates the severity of the memory-related event.
                   If the event is TRIM_MEMORY_RUNNING_CRITICAL, then the system will
                   begin killing background processes.
                */
            }

            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND,
            ComponentCallbacks2.TRIM_MEMORY_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_COMPLETE -> {
                /*
                   Release as much memory as the process can.

                   The app is on the LRU list and the system is running low on memory.
                   The event raised indicates where the app sits within the LRU list.
                   If the event is TRIM_MEMORY_COMPLETE, the process will be one of
                   the first to be terminated.
                */
            }

            else -> {
                /*
                  Release any non-critical data structures.

                  The app received an unrecognized memory level value
                  from the system. Treat this as a generic low-memory message.
                */
            }
        }
    }
}