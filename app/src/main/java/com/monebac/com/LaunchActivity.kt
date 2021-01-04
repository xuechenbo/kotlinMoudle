package com.monebac.com

import com.alibaba.android.arouter.launcher.ARouter
import com.monebac.common_base.base.mBaseActivity
import com.monebac.common_base.constants.ARouterConfig
import kotlinx.android.synthetic.main.launch_layout.*

class LaunchActivity : mBaseActivity() {

    override fun initLayout(): Int = R.layout.launch_layout

    override fun initData() {
        btn_login.setOnClickListener {
            ARouter.getInstance().build(ARouterConfig.LOGIN_MOUDLE).navigation()
            finish()
        }
    }

}