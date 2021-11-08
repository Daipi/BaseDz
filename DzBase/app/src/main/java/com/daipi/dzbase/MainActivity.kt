package com.daipi.dzbase

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.daipi.base.autoservice.BaseServiceLoader
import com.daipi.base.utils.LogUtil
import com.daipi.common.Cat
import com.daipi.common.autoservice.IPracticeService
import com.daipi.common.autoservice.ITestFmActService
import com.daipi.common.bus.BusKey
import com.daipi.common.bus.TestEvent
import com.daipi.dzbase.databinding.ActivityMainBinding
import com.daipi.dzbase.room.User
import com.daipi.dzbase.viewmodel.MainTestModel
import com.jeremyliao.liveeventbus.LiveEventBus
import dagger.hilt.android.AndroidEntryPoint


import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cat: Cat

    @Inject
    lateinit var chinaCar: ChinaCar

    @Inject
    lateinit var dog: Dog

    lateinit var bind: ActivityMainBinding

    private val mainModel: MainTestModel by viewModels()

    val MYPERMISSIONS = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestWithPermissions(*MYPERMISSIONS)
        //setContentView(R.layout.activity_main)
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val userObserver = Observer<User> { user ->
            bind.tvMainApp.text = user.firstName
        }
        mainModel.user.observe(this, userObserver)
/*    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }*/
        registerBus()
    }

    private fun registerBus() {
        LiveEventBus
            .get(TestEvent::class.java)
            .observe(this,
                {
                    LogUtil.d("LiveDataBus ****MainActivity",it)
                    mainModel.user.value = User(null,it.content,it.content) })
    }

    fun toPractice(view: View) {
        //LiveDataBus.get().with("testBus").value = "哈哈哈LiveDataBus"
/*        BaseServiceLoader.load(IPracticeService::class.java)
            ?.toPractice(this, cat.name)*/
        BaseServiceLoader.load(ITestFmActService::class.java)?.toTestFragment(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d("LiveDataBus ****MainActivity onDestroy()")
    }
}