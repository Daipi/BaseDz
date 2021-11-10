package com.daipi.practice.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.daipi.base.base.BaseBindFragment
import com.daipi.practice.R
import com.daipi.practice.databinding.FragmentOneBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */
@AndroidEntryPoint
class FragmentOne : BaseBindFragment() {
    private val viewModel: TestFmModel by activityViewModels()
    private lateinit var bind: FragmentOneBinding

    override fun getContentViewId(): Int = R.layout.fragment_one

    override fun initView() {
        bind = getDataBinding()
        val textObserver = Observer<String> { text ->
            bind.fmOneTv.text = text
        }
        viewModel.text.observe(this, textObserver)

        bind.module = viewModel

        bind.fmOneTv.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_fragmentOne_to_mainPage1Fragment) }
    }
}