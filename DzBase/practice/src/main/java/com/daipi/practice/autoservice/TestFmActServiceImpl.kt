package com.daipi.practice.autoservice

import android.content.Context
import android.content.Intent
import com.daipi.common.autoservice.ITestFmActService
import com.daipi.practice.PracticeActivity
import com.daipi.practice.TestFragmentActivity
import com.google.auto.service.AutoService

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */
@AutoService(ITestFmActService::class)
class TestFmActServiceImpl : ITestFmActService{
    override fun toTestFragment(context: Context) {
        context.startActivity(Intent(context, TestFragmentActivity::class.java))
    }
}