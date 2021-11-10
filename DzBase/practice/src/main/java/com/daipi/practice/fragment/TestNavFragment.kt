package com.daipi.practice.fragment

import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.daipi.base.base.BaseBindFragment
import com.daipi.base.utils.ToastUtil
import com.daipi.common.MmkvUtil
import com.daipi.practice.R
import com.daipi.practice.databinding.FragmentTestNavBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


/**
 * author:daijs
 * time:2021/11/9
 * details:
 */
@AndroidEntryPoint
class TestNavFragment : BaseBindFragment() {
    private lateinit var binding: FragmentTestNavBinding
    private val viewModel: TestFmModel by activityViewModels()

    private lateinit var obj: ViewPager2.OnPageChangeCallback
    private lateinit var adapter: TestPagerAdapter

    override fun getContentViewId(): Int = R.layout.fragment_test_nav

    override fun initView() {
        binding = getDataBinding()
        MmkvUtil.getKv()?.encode("fw_count","初始化")
        adapter = TestPagerAdapter(requireActivity())//activity?.let { TestPagerAdapter(it) }!!
        binding.actTfVp.adapter = adapter
        TabLayoutMediator(binding.actTfTab, binding.actTfVp) {
                tab, position -> tab.text = "第" + position + "页"
        }.attach()

        initListener()

        binding.actTfToolbar.setNavigationOnClickListener { requireActivity().finish() }
        binding.actTfToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.filter_test1 -> {
                    ToastUtil.show("菜单1")
                    true
                }
                R.id.filter_test2 -> {
                    ToastUtil.show("菜单2")
                    true
                }
                R.id.filter_test3 -> {
                    ToastUtil.show("菜单3")
                    true
                }
                else -> false
            }
        }
        binding.actTfToolbar.menu
    }

    private fun initListener() {
        obj = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                onChange(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        }
        binding.actTfVp.registerOnPageChangeCallback(obj)
    }

    fun onChange(position: Int) {
        when (position) {
            0 -> {
                MmkvUtil.getKv()?.encode("fw_count","第一页")
                viewModel.getTextData()
            }
            1 -> {
                MmkvUtil.getKv()?.encode("fw_count","第二页")
                viewModel.getTextData()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.actTfVp.unregisterOnPageChangeCallback(obj)
    }
}