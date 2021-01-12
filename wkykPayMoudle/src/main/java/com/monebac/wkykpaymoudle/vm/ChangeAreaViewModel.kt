package com.monebac.wkykpaymoudle.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.common_base.base.BaseViewModel
import com.monebac.common_base.utils.getList
import com.monebac.wkykpaymoudle.bean.AreaModel

class ChangeAreaViewModel : BaseViewModel() {

    val cityob = MutableLiveData<List<AreaModel>>()
    val isType = MutableLiveData<Boolean>()
    val industryob = MutableLiveData<String>()

    fun getCity(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        cityob.value = getList(result.str38)
                        //TODO 请求省还是市
                        isType.value = result.str41 == "0"
                    }
                },
                error = {

                }
        )
    }

    fun getIndustry(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    tipString.value = result.str39
                    if (result.str39 == "00") {
                        industryob.value = result.str38
                    }
                }
        )
    }


}