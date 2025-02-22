package com.monebac.wkykpaymoudle.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.monebac.wkykpaymoudle.R
import com.monebac.wkykpaymoudle.bean.AreaModel

class ChangeAreaAdapter(res: Int, data: List<AreaModel>) : BaseQuickAdapter<AreaModel, BaseViewHolder>(res, data) {
    override fun convert(helper: BaseViewHolder, item: AreaModel) {
        helper.setText(R.id.tv_area, item.divisionName)
    }
}