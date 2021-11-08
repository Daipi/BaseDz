package com.daipi.practice

import android.content.Intent
import android.os.Bundle
import com.daipi.base.base.BaseBindActivity
import com.daipi.base.utils.ToastUtil
import com.daipi.practice.databinding.ActivityPracticeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PracticeActivity : BaseBindActivity() {
    private lateinit var binding:ActivityPracticeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getDataBinding()

        /*AppExecutors.instance.mainThread()?.execute(Runnable {
            bingding.tvTitle.text = MmkvUtil.getKv()?.decodeString("daipi")
        })*/

        //接收传值
        ToastUtil.show(intent?.getStringExtra("msg") ?:"")

        startActivity(Intent(this,TestFragmentActivity::class.java))
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_practice
    }
}