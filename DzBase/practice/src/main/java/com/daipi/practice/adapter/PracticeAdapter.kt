package com.daipi.practice.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import com.daipi.base.base.BaseRecyclerAdapter
import com.daipi.base.base.BaseViewHolder
import com.daipi.practice.R
import com.daipi.practice.TestData

class PracticeAdapter(context: Context?, list: List<TestData?>?) :
    BaseRecyclerAdapter<TestData?>(context, list) {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.test_item
    }

    @SuppressLint("SetTextI18n")
    override fun bindData(holder: BaseViewHolder?, item: TestData?, position: Int) {
        val tvId = holder?.findViewById<AppCompatTextView>(R.id.tv_item_id)
        val tvName = holder?.findViewById<AppCompatTextView>(R.id.tv_item_name)

        tvId?.text = "" + item?.id
        tvName?.text = item?.name
    }
}