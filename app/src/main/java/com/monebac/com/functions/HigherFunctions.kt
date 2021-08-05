package com.monebac.com.functions

import android.util.Log

/**
 *   created by xxx
 *   on 2021/7/29
 *   高阶函数
 */


/**
 * (String,Int) -> String
 *
 * 接收的参数，    返回值
 */

fun main() {
    getA { str, num ->
        str + num
    }

    a { str, num ->
        str
    }
    //TODO eg1:
    addNum(2, 3, ::plus)
    addNum(2, 3, ::minus)
    //TODO eg2
    addNum(2, 3) { num1, num2 ->
        num1 + num2
    }
    addNum(2, 3) { num1, num2 ->
        num1 - num2
    }
        logd("ssss"){
            ""
        }
}


val a = fun(fus: (String, Int) -> String): String {
    return fus("abc", 1)
}

fun getA(fus: (String, Int) -> String): String {
    return fus("abc", 1)
}


inline fun addNum(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}


//TODO 1
val sum = { x: Int, y: Int -> x + y }

//TODO 显示类型声明
var sum1: (Int, Int) -> Int = { num1, num2 ->
    num1 + num2
}



private fun logd(tag: String, msg: () -> String){
    Log.d(tag, ">>> ${msg()}" )
}

/**
 * https://juejin.cn/post/6903465253321834509#heading-1   内联函数
 */


























