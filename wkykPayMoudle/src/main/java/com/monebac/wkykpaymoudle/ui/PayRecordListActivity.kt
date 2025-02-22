package com.monebac.wkykpaymoudle.ui

import androidx.lifecycle.Observer
import com.monebac.common_base.base.BaseVmActivity
import com.monebac.common_base.utils.getMap
import com.monebac.wkykpaymoudle.R
import com.monebac.wkykpaymoudle.adapter.PayRecordAdapter
import com.monebac.wkykpaymoudle.bean.PayItemModel
import com.monebac.wkykpaymoudle.vm.PayRecordViewModel
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_pay_record_list.*
import kotlinx.android.synthetic.main.layout_title.*

class PayRecordListActivity : BaseVmActivity<PayRecordViewModel>() {
    private lateinit var mList: MutableList<PayItemModel>
    private lateinit var payRecordAdapter: PayRecordAdapter
    var page: Int = 1

    override fun layoutRes(): Int = R.layout.activity_pay_record_list

    override fun viewModelClass(): Class<PayRecordViewModel> = PayRecordViewModel::class.java

    override fun initView() {
        mList = ArrayList()
        tv_title.text = "交易提醒"
    }

    override fun initData() {
        back.setOnClickListener { finish() }
        smarFresh.run {
            refreshHeader = ClassicsHeader(context)
            refreshFooter = ClassicsFooter(context)
            setOnRefreshListener {
                mList.clear()
                page = 1
                mViewModel.getContent(requestData())
            }
            setOnLoadmoreListener {
                page++
                mViewModel.getContent(requestData(page))
            }
        }
        payRecordAdapter = PayRecordAdapter(mList).also {
            it.bindToRecyclerView(recyCler)
        }
        mViewModel.getContent(requestData())
    }

    override fun observe() {
        super.observe()
        mViewModel.mContent.observe(this, Observer {
            smarFresh.finishRefresh()
            smarFresh.finishLoadmore()
            mList.addAll(it)
            payRecordAdapter.setNewData(mList)
        })
    }

    private fun requestData(page: Int = 1): MutableMap<String, String> = getMap(mutableMapOf(
            "3" to "081424",
            "32" to "$page",
            "33" to "10"
    ))
}