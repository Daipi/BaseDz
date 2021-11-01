package com.daipi.practice.fragment

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.daipi.base.base.BaseBindFragment
import com.daipi.base.interceptor.MyContinuationInterceptor
import com.daipi.base.utils.ContextUtils
import com.daipi.base.utils.LogUtil
import com.daipi.common.GlideUtils
import com.daipi.common.bus.TestEvent
import com.daipi.practice.R
import com.daipi.practice.TestData
import com.daipi.practice.adapter.PracticeAdapter
import com.daipi.practice.databinding.FragmentTestLayoutBinding
import com.daipi.practice.viewmodel.PracticeModel
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.coroutines.*
import java.util.ArrayList

/**
 * author:daijs
 * time:2021/11/1
 * details:
 */
class TestFragment: BaseBindFragment() {
    private val testImgUrl = "https://img2018.cnblogs.com/blog/1467574/201901/1467574-20190128094402634-1200329139.jpg"
    private var title = "标题一"
    private var list: ArrayList<TestData> = ArrayList<TestData>()
    private lateinit var adapter : PracticeAdapter
    private val viewModel: PracticeModel by viewModels()
    private lateinit var binding:FragmentTestLayoutBinding

    override fun getContentViewId(): Int {
        return R.layout.fragment_test_layout
    }

    override fun initView() {
        binding = getDataBinding()
        binding.practice = viewModel

        GlideUtils.loadImage(ContextUtils.context,testImgUrl,binding.ivMainBg)
        for (i in 1..10) {
            list.add(TestData(i,"daipi"))
        }
        adapter = PracticeAdapter(context,list)
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        binding.rvMainList.layoutManager = manager
        binding.rvMainList.adapter = adapter
        //GlobalScope.launch(MyContinuationInterceptor()) { test() }
        MainScope().launch(MyContinuationInterceptor()) { test() }
        registerBus()
    }

    private fun registerBus() {
        LiveEventBus
            .get(TestEvent::class.java)
            .observe(this,
                {
                    LogUtil.d("LiveDataBus ****PracticeActivity",it)
                    binding.tvTitle.text = it.content })
    }

    suspend fun test() = withContext(Dispatchers.Main){
        LogUtil.d(1)
        delay(2000)
        LiveEventBus.get(TestEvent::class.java).post(TestEvent("Hello"))
/*        val job = viewModel.viewModelScope.async(CoroutineName("XC02"),start = CoroutineStart.LAZY) {
            LogUtil.d("02")
            999
        }
        val result = job.await()
        LogUtil.d("job $result")
        LogUtil.d(3)
        LogUtil.d(4)*/
    }

}