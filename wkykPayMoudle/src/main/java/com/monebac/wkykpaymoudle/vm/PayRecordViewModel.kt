package com.monebac.wkykpaymoudle.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.common_base.base.BaseViewModel
import com.monebac.common_base.utils.getList
import com.monebac.wkykpaymoudle.bean.PayItemModel

class PayRecordViewModel : BaseViewModel() {

    val mContent = MutableLiveData<List<PayItemModel>>()

    fun getContent(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        mContent.value = getList<List<PayItemModel>>(result.str32)
                    }
                },
                error = {

                }
        )
    }
}