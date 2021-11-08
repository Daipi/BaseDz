package com.daipi.practice.fragment

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * author:daijs
 * time:2021/11/8
 * details:
 */


const val ONE_PAGE_INDEX = 0
const val TWO_PAGE_INDEX = 1


class TestPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ONE_PAGE_INDEX to { FragmentOne() },
        TWO_PAGE_INDEX to { FragmentTwo() },
/*        2 to { FragmentTwo() },
        3 to { FragmentTwo() },
        4 to { FragmentTwo() },
        5 to { FragmentTwo() },
        6 to { FragmentTwo() },
        7 to { FragmentTwo() },
        8 to { FragmentTwo() },
        9 to { FragmentTwo() },
        10 to { FragmentTwo() }*/
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment =
        tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
}