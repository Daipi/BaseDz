package com.daipi.practice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.daipi.base.base.BaseBindFragment
import com.daipi.practice.R
import com.daipi.practice.databinding.FragmentMainPage1Binding
import com.daipi.practice.databinding.FragmentMainPage2Binding
import com.daipi.practice.databinding.FragmentMainPage3Binding
import com.daipi.practice.databinding.FragmentOneBinding

/**
 * author:daijs
 * time:2021/11/9
 * details:
 */
class MainPage1Fragment : BaseBindFragment() {
    private lateinit var bind: FragmentMainPage1Binding
    override fun getContentViewId(): Int = R.layout.fragment_main_page1

    override fun initView() {
        bind = getDataBinding()
        bind.fmPage1Tv.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_mainPage1Fragment_to_mainPage2Fragment) }
    }
}

class MainPage2Fragment : BaseBindFragment() {
    private lateinit var bind: FragmentMainPage2Binding
    override fun getContentViewId(): Int = R.layout.fragment_main_page2

    override fun initView() {
        bind = getDataBinding()
        bind.fmPage2Tv.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_mainPage2Fragment_to_mainPage3Fragment) }
        bind.fmPage2Tv2.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_mainPage2Fragment_to_fragmentOne) }
    }
}

class MainPage3Fragment : BaseBindFragment() {
    private lateinit var bind: FragmentMainPage3Binding
    override fun getContentViewId(): Int = R.layout.fragment_main_page3

    override fun initView() {
        bind = getDataBinding()//
        //bind.fmPage3Tv.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_mainPage1Fragment_to_mainPage2Fragment) }
    }
}