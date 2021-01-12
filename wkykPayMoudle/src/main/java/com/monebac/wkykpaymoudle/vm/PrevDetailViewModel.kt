package com.monebac.wkykpaymoudle.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.common_base.base.BaseViewModel

class PrevDetailViewModel : BaseViewModel() {
    val succType = MutableLiveData<String>()
    fun subPlan(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        succType.value = "1"
                    }
                }
        )
    }
}