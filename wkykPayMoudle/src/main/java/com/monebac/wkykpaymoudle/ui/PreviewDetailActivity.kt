package com.monebac.wkykpaymoudle.ui

import androidx.lifecycle.Observer
import com.monebac.common_base.base.BaseVmActivity
import com.monebac.common_base.utils.getList
import com.monebac.common_base.utils.getMap
import com.monebac.common_base.utils.getTimeCurrYMD
import com.monebac.wkykpaymoudle.R
import com.monebac.wkykpaymoudle.adapter.PreviewDetailAdapter
import com.monebac.wkykpaymoudle.bean.BindCard
import com.monebac.wkykpaymoudle.bean.IndustryModel
import com.monebac.wkykpaymoudle.bean.PlanItemModel
import com.monebac.wkykpaymoudle.bean.PreviewPlanModel
import com.monebac.wkykpaymoudle.event.ClosePlanEvent
import com.monebac.wkykpaymoudle.vm.PrevDetailViewModel
import kotlinx.android.synthetic.main.act_preview_detail.*
import kotlinx.android.synthetic.main.layout_title.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.toast

class PreviewDetailActivity : BaseVmActivity<PrevDetailViewModel>() {
    lateinit var previewPlanModel: PreviewPlanModel
    lateinit var bindCard: BindCard
    lateinit var previewDetailAdapter: PreviewDetailAdapter
    lateinit var mList: List<PlanItemModel>

    override fun layoutRes(): Int = R.layout.act_preview_detail

    override fun viewModelClass(): Class<PrevDetailViewModel> = PrevDetailViewModel::class.java

    override fun initView() {
        tv_title.text = "提交计划"
        mList = ArrayList()
        previewPlanModel = intent.getSerializableExtra("previewPlanModel") as PreviewPlanModel
        bindCard = intent.getSerializableExtra("bindCard") as BindCard
    }

    override fun initData() {
        back.setOnClickListener { finish() }
        acqChName.text = previewPlanModel.acqName
        tv_preRepayAmount.text = previewPlanModel.HkMoney
        tv_total_service_price.text = previewPlanModel.totalFee
        tv_RNum.text = "${previewPlanModel.rNum}笔"

        val planItemList = getList<List<PlanItemModel>>(previewPlanModel.planDetail)

        val industryStr = previewPlanModel.industryStr
        val list = getList<List<IndustryModel>>(industryStr)
        val address = previewPlanModel.address

        mList = planItemList.map {
            it.run {
                customizecity = "${address?.run {
                    "${get("pri")!!.divisionName}-${get("city")!!.divisionName}"
                }}"
                val random = (list.indices).random()
                industryName = list[random].acqMerchantName
            }
            it
        }

        previewDetailAdapter = PreviewDetailAdapter(R.layout.item_preview_detail_plan, mList).also {
            recyCler.adapter = it
        }

        bt_submit_plan.setOnClickListener {
            mViewModel.subPlan(submitPlan())
        }
    }

    override fun observe() {
        super.observe()
        mViewModel.succType.observe(this, Observer {
            toast("提交成功")
            EventBus.getDefault().post(ClosePlanEvent())
            finish()
        })
    }


    private fun submitPlan(): MutableMap<String, String> = getMap(mutableMapOf(
            "3" to "190210",
            "5" to "1",
            "8" to previewPlanModel.HkMoney,
            "9" to previewPlanModel.ZzjMoney,
            "10" to previewPlanModel.startTime!!.getTimeCurrYMD().toString(),
            "11" to previewPlanModel.endTime!!.getTimeCurrYMD().toString(),
            "12" to previewPlanModel.fee,
            "13" to previewPlanModel.timeFee,
            "14" to previewPlanModel.rNum,
            "43" to previewPlanModel.AcqCode,
            "57" to mList.toString()
    ))
}