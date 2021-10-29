package com.daipi.base.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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
abstract class BaseBindActivity : AppCompatActivity(), CoroutineScope by MainScope() {
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
}