package com.monebac.wkykpaymoudle.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.common_base.base.BaseViewModel
import com.monebac.common_base.utils.getList
import com.monebac.wkykpaymoudle.bean.BindCard

class BankCardViewModel : BaseViewModel() {
    override fun onCleared() {
        super.onCleared()
    }

    val bankList = MutableLiveData<List<BindCard>>()

    fun getBankList(map: MutableMap<String, String>) {
        launch(
                block = {
                    loading.value = true
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        bankList.value = getList(result.str57)
                    }
                    loading.value = false
                },
                error = {

                }
        )
    }

}