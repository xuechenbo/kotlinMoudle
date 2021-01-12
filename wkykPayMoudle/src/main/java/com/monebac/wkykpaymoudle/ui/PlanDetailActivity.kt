package com.monebac.wkykpaymoudle.ui

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.monebac.common_base.base.BaseVmActivity
import com.monebac.common_base.utils.getMap
import com.monebac.wkykpaymoudle.R
import com.monebac.wkykpaymoudle.adapter.PlanDetailAdapter
import com.monebac.wkykpaymoudle.bean.BindCard
import com.monebac.wkykpaymoudle.bean.PlanAllModel
import com.monebac.wkykpaymoudle.bean.PlanItemDetailModel
import com.monebac.wkykpaymoudle.vm.PlanDetailViewModel
import kotlinx.android.synthetic.main.activity_plan_detail.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast

class PlanDetailActivity : BaseVmActivity<PlanDetailViewModel>() {
    private lateinit var planAllModel: PlanAllModel
    private lateinit var bindCard: BindCard
    private lateinit var planDetailAdapter: PlanDetailAdapter
    private var mList: List<PlanItemDetailModel> = ArrayList()

    override fun viewModelClass(): Class<PlanDetailViewModel> = PlanDetailViewModel::class.java

    override fun layoutRes(): Int = R.layout.activity_plan_detail

    override fun initView() {
        tv_title.text = "计划详情"
        planAllModel = intent.getSerializableExtra("planDetail") as PlanAllModel
        bindCard = intent.getSerializableExtra("bindCard") as BindCard
    }

    override fun initData() {
        back.setOnClickListener { finish() }
        planDetailAdapter = PlanDetailAdapter(mList).also {
            recyCler.adapter = it
        }
        recyCler.run {
            //TODO NestedScrollView 嵌套recyclerView 滑动卡顿问题  对于老版本手机(api21-)，处理方式如下
            layoutManager = LinearLayoutManager(context).apply {
                //isSmoothScrollbarEnabled = true
                //isAutoMeasureEnabled = true
            }
            //TODO api21+  以上的不卡顿
            isNestedScrollingEnabled = false
            //setHasFixedSize(true)
        }
        tv_status.setOnClickListener {
            when (tv_status.text) {
                "查看更多" -> {
                    tv_status.text = "收起更多"
                    planDetailAdapter.setShowOnlyItem(false)
                }
                else -> {
                    tv_status.text = "查看更多"
                    planDetailAdapter.setShowOnlyItem(true)
                }
            }
        }
        bt_stopPlan.setOnClickListener {
            toast("没写别点了")
        }

        mViewModel.getDetailPlan(getMap(
                mutableMapOf("3" to "190213"
                        , "8" to planAllModel.ID))
        )
    }

    override fun observe() {
        super.observe()
        mViewModel.detail.observe(this, Observer {
            planDetailAdapter.setNewData(it)
            planDetailAdapter.setShowOnlyItem(true)
        })
    }
}