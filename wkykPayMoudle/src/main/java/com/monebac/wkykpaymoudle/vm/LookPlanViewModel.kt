package com.monebac.wkykpaymoudle.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.common_base.base.BaseViewModel
import com.monebac.common_base.utils.getList
import com.monebac.wkykpaymoudle.bean.PlanAllModel

class LookPlanViewModel : BaseViewModel() {
    var lookup: MutableLiveData<List<PlanAllModel>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
    }

    fun getListData(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        lookup.value = getList(result.str57)
                    }
                },
                error = {

                }
        )
    }

}