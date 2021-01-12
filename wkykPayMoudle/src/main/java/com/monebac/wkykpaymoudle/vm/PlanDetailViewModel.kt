package com.monebac.wkykpaymoudle.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.common_base.base.BaseViewModel
import com.monebac.common_base.utils.getList
import com.monebac.wkykpaymoudle.bean.PlanItemDetailModel

class PlanDetailViewModel : BaseViewModel() {
    val detail = MutableLiveData<List<PlanItemDetailModel>>()

    fun getDetailPlan(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        detail.value = getList(result.str57)
                    }
                }
        )

    }
}