package com.monebac.sharemoudle

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.monebac.common_base.base.mBaseActivity
import com.monebac.common_base.constants.ARouterConfig
import kotlinx.android.synthetic.main.layout_title.*

@Route(path = ARouterConfig.SHARE_MOUDLE)
class ShareActivity : mBaseActivity() {
    override fun initLayout(): Int {
        return R.layout.act_share
    }

    override fun initData() {
        back.setOnClickListener { finish() }
        tv_title.text = "分享"
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }

}