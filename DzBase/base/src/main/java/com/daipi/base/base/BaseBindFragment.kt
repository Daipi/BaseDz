package com.daipi.base.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * author:daijs
 * time:2021/11/1
 * details:
 */
abstract class BaseBindFragment: Fragment() {
    private var dataBinding: ViewDataBinding? = null
    protected var context: Activity? = null
    open fun <Bind : ViewDataBinding?> getDataBinding(): Bind {
        return dataBinding as Bind
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        context = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            dataBinding = DataBindingUtil.inflate(
                inflater,
                getContentViewId(),
                container,
                false
            )
        initView()
        return dataBinding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dataBinding != null) dataBinding!!.unbind()
    }

    /**
     * @return id
     *
     */
    abstract fun getContentViewId(): Int

    abstract fun initView()
}