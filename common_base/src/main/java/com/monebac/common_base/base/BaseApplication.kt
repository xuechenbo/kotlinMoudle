package com.monebac.common_base.base

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.monebac.common_base.BuildConfig

open class BaseApplication : Application() {
    companion object {
        lateinit var application: BaseApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        application = this
        //MultiDex分包方法 初始化
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        //初始化ARouter
        initARouter()
    }


    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            Log.e("初始化", "初始化Arouter")
        }
        ARouter.init(application)
    }


}