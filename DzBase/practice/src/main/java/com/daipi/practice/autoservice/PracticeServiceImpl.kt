package com.daipi.practice.autoservice

import android.content.Context
import android.content.Intent
import com.daipi.common.autoservice.IPracticeService
import com.daipi.practice.PracticeActivity
import com.google.auto.service.AutoService

@AutoService(IPracticeService::class)
class PracticeServiceImpl : IPracticeService{
    override fun toPractice(context: Context, msg: String) {
        context.startActivity(Intent(context, PracticeActivity::class.java).apply { putExtra("msg", msg) })
    }
}