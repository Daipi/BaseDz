package com.daipi.practice.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.daipi.base.base.BaseBindFragment
import com.daipi.practice.R
import com.daipi.practice.databinding.FragmentTwoBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */
@AndroidEntryPoint
class FragmentTwo : BaseBindFragment() {
    private val viewModel: TestFmModule by activityViewModels()
    private lateinit var bind: FragmentTwoBinding

    override fun getContentViewId(): Int = R.layout.fragment_two

    override fun initView() {
        bind = getDataBinding()
        val textObserver = Observer<String> { text ->
            bind.fmTwoTv.text = text
        }
        viewModel.text.observe(this, textObserver)

        bind.module = viewModel
    }
}