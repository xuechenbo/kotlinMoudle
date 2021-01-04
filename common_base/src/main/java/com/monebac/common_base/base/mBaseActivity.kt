package com.monebac.common_base.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import org.jetbrains.anko.toast

abstract class mBaseActivity : FragmentActivity() {
    lateinit var context: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(initLayout())
        //设置数据
        initData()
        //全局设置竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    abstract fun initLayout(): Int


    abstract fun initData()

}
