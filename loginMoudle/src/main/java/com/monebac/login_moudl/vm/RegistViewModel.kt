package com.monebac.login_moudl.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.common_base.base.BaseViewModel
import com.monebac.common_base.network.ResultRepository

class RegistViewModel : BaseViewModel() {
    private val reRepository by lazy {
        ResultRepository()
    }

    val mCodeObser = MutableLiveData<String>()

    fun getCode(map: MutableMap<String, String>) {
        launch(block = {
            loading.value = true
            val result = reRepository.getResult(map)
            mCodeObser.value = if (result.str39 == "00") "验证码发送成功" else result.str39
            loading.value = false
        },
                error = {
                    loading.value = false
                })
    }

    fun register() {
        mCodeObser.value = "功能没写"
    }

}