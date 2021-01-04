package com.monebac.login_moudl.ui

import androidx.lifecycle.Observer
import com.monebac.common_base.base.BaseVmActivity
import com.monebac.common_base.utils.getMap
import com.monebac.login_moudl.R
import com.monebac.login_moudl.vm.RegistViewModel
import kotlinx.android.synthetic.main.activity_regitser.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast

class RegistActivity : BaseVmActivity<RegistViewModel>() {
    override fun viewModelClass(): Class<RegistViewModel> = RegistViewModel::class.java

    override fun layoutRes() = R.layout.activity_regitser

    override fun initView() {
        tv_title.text = "注册"
    }

    override fun initData() {
        back.setOnClickListener { finish() }
        tv_get_code.setOnClickListener {
            if (et_phone.text.toString().isEmpty()) {
                toast("请输入手机号")
                return@setOnClickListener
            }
            mViewModel.getCode(getMap(mutableMapOf(
                    "1" to et_phone.text.toString(),
                    "3" to "190919")))
        }
        bt_register.setOnClickListener {
            when {
                et_phone.text.isNullOrEmpty() -> toast("手机号为空")
                et_code.text.isNullOrEmpty() -> toast("验证码为空")
                et_code.text.length != 6 -> toast("请检查验证码")
                et_pwd.text.isNullOrEmpty() -> toast("密码为空")
                et_Rpwd.text.isNullOrEmpty() -> toast("确认密码为空")
                et_pwd.text.toString() != et_Rpwd.text.toString() -> toast("两次密码不一样")
                et_parentPhoen.text.isNullOrEmpty() -> toast("请输入上级联系人")
                else -> {
                    mViewModel.register()
                }
            }
        }

    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            mCodeObser.observe(this@RegistActivity, Observer {
                toast(it)
            })
            loading.observe(this@RegistActivity, Observer {
                if (it) showProgressDialog(R.string.loading) else dismissProgressDialog()
            })
        }
    }


}