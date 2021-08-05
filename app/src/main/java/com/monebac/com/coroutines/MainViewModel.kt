package com.monebac.com.coroutines

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by xxx
 * on 2021/7/28
 */
class MainViewModel : ViewModel() {
    val flag = MutableLiveData<String>()
    fun checkMsg() {
        viewModelScope.launch(Dispatchers.Main) {
            val withContext = withContext(Dispatchers.IO) {
                searchFromNet()
            }
            flag.value = withContext
        }
    }

    suspend fun searchFromNet(): String {
        Log.d("coroutine", "searchFromNet: ${Thread.currentThread().name}")
        delay(3_000)
        return "咿哈哈哈"
    }


    suspend fun searchFromNet1(): String {
        Log.d("coroutine", "searchFromNet: ${Thread.currentThread().name}")
        delay(5_000)
        return "咿呀呀呀"
    }


    suspend fun getAb(){
        withContext(Dispatchers.IO){

        }
    }
}