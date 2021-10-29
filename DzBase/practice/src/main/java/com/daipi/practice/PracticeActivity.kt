package com.daipi.practice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.daipi.base.base.BaseBindActivity
import com.daipi.base.interceptor.MyContinuationInterceptor
import com.daipi.base.utils.ContextUtils
import com.daipi.base.utils.LogUtil
import com.daipi.base.utils.ToastUtil
import com.daipi.common.GlideUtils
import com.daipi.common.bus.BusKey
import com.daipi.common.bus.TestEvent
import com.daipi.practice.adapter.PracticeAdapter
import com.daipi.practice.databinding.ActivityPracticeBinding
import com.daipi.practice.viewmodel.PracticeModel
import com.jeremyliao.liveeventbus.LiveEventBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.ArrayList

@AndroidEntryPoint
class PracticeActivity : BaseBindActivity() {
    private lateinit var binding:ActivityPracticeBinding
    private val testImgUrl = "https://img2018.cnblogs.com/blog/1467574/201901/1467574-20190128094402634-1200329139.jpg"
    private var title = "标题一"
    private var list: ArrayList<TestData> = ArrayList<TestData>()
    private lateinit var adapter : PracticeAdapter
    private val viewModel: PracticeModel by viewModels()
    //private val testModel: testModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getDataBinding()

        /*AppExecutors.instance.mainThread()?.execute(Runnable {
            bingding.tvTitle.text = MmkvUtil.getKv()?.decodeString("daipi")
        })*/
        //数据变化监听
/*        binding.practice = viewModel
        val userObserver = Observer<User> { user ->
            binding.tvTitle.text = user.firstName
            binding.tvContent.text = user.lastName
        }
        viewModel.user.observe(this, userObserver)

        //点击事件
        binding.tvTitle.setOnClickListener {
            ToastUtil.showCenterToast("ddd")
            viewModel.user.value = User(2,"TZM","SD")
        }*/
        binding.practice = viewModel

        //接收传值
        ToastUtil.show(intent?.getStringExtra("msg") ?:"")

        GlideUtils.loadImage(ContextUtils.context,testImgUrl,binding.ivMainBg)

/*        NetWorkApi.getInstance().getProject2(object : ResultObserver<List<ProjectType>>(){

            override fun onSuccess(entity: List<ProjectType>?) {
                if (entity != null) {
                    LogUtil.d(TAG, entity.size)
                }
            }

            override fun onFail(msg: String?) {
                if (msg != null) {
                    LogUtil.d(TAG, msg)
                }
            }
        })*/
        for (i in 1..10) {
            list.add(TestData(i,"daipi"))
        }
        adapter = PracticeAdapter(this,list)
        val manager = LinearLayoutManager(this)
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

    override fun getContentViewId(): Int {
        return R.layout.activity_practice
    }
}