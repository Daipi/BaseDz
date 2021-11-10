package com.daipi.practice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.daipi.base.base.BaseBindFragmentActivity
import com.daipi.base.utils.ToastUtil
import com.daipi.common.MmkvUtil
import com.daipi.practice.databinding.ActivityTestFragmentBinding
import com.daipi.practice.fragment.TestFmModule
import com.daipi.practice.fragment.TestPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */
@AndroidEntryPoint
class TestFragmentActivity : BaseBindFragmentActivity() {

    private lateinit var binding: ActivityTestFragmentBinding
/*    private lateinit var obj: ViewPager2.OnPageChangeCallback
    private lateinit var adapter: TestPagerAdapter
    private val viewModel: TestFmModule by viewModels()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getDataBinding()


    }



    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_test_fragment
    }

/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_test, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.filter_test1 -> {
            ToastUtil.show("菜单1")
            true
        }
        R.id.filter_test1 -> {
            ToastUtil.show("菜单2")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }*/
}