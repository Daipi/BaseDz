package com.daipi.practice.fragment

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.daipi.base.base.BaseBindFragment
import com.daipi.base.utils.ToastUtil
import com.daipi.practice.R
import com.daipi.practice.databinding.FragmentOneBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */
@AndroidEntryPoint
class FragmentOne : BaseBindFragment() {
    private val viewModel: TestFmModel by navGraphViewModels(R.id.nav_graph)
    private lateinit var bind: FragmentOneBinding

    override fun getContentViewId(): Int = R.layout.fragment_one

    override fun initView() {
        bind = getDataBinding()
        val textObserver = Observer<String> { text ->
            bind.fmOneTv.text = text
        }
        viewModel.text.observe(this, textObserver)

        bind.module = viewModel

        GlobalScope.launch(Dispatchers.Main) {
            //viewModel.lateText.collect { text -> ToastUtil.show("" + text)}

            viewModel.amendLateText.collect { text -> ToastUtil.show("" + text) }
        }
        getActivityFromView(bind.fmOneIv)

        bind.fmOneTv.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_fragmentOne_to_mainPage1Fragment) }
    }

    /**
     * 尝试从视图中获取Activity.
     * 托管在浮动窗口(如dialog和toast)上的视图肯定会返回null.
     * @return host activity; or null if not available
     */
    //从View获取真正的Activity
    fun getActivityFromView(view: View): Activity? {
        var context = view.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

}