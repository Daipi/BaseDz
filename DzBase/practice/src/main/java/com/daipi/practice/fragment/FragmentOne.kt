package com.daipi.practice.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.daipi.base.base.BaseBindFragment
import com.daipi.base.utils.ToastUtil
import com.daipi.practice.R
import com.daipi.practice.databinding.FragmentOneBinding

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */
class FragmentOne : BaseBindFragment() {
    private val viewModel: TestFmModule by activityViewModels()
    private lateinit var bind: FragmentOneBinding

    override fun getContentViewId(): Int = R.layout.fragment_one

    override fun initView() {
        bind = getDataBinding()
        val textObserver = Observer<String> { text ->
            bind.fmOneTv.text = text
        }
        viewModel.text.observe(this, textObserver)

        bind.module = viewModel
    }
}