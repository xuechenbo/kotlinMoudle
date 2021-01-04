package com.monebac.login_moudl.ui

import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.monebac.common_base.Constant
import com.monebac.common_base.base.BaseVmActivity
import com.monebac.common_base.constants.ARouterConfig
import com.monebac.common_base.utils.PreferencesUtil
import com.monebac.common_base.utils.getMap
import com.monebac.login_moudl.R
import com.monebac.login_moudl.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_new_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Route(path = ARouterConfig.LOGIN_MOUDLE)
class LoginActivity : BaseVmActivity<LoginViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_new_login

    override fun viewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }


    override fun initView() {
        val phone = PreferencesUtil.getString("phone")
        val pwd = PreferencesUtil.getString("pwd")
        if (phone.isNotEmpty() || pwd.isNotEmpty()) {
            et_phone.setText(phone)
            et_pass.setText(pwd)
        }
    }

    override fun initData() {
        btn_login.setOnClickListener {
            when {
                et_phone.text.isNullOrEmpty() -> toast("手机号为空")
                et_pass.text.isNullOrEmpty() -> toast("密码为空")
                else -> mViewModel.login(
                        getMap(mutableMapOf(
                                "1" to et_phone.text.toString(),
                                "3" to "190928",
                                "8" to Constant.Md5(et_pass.text.toString())))
                )
            }
        }
        tv_register.setOnClickListener {
            startActivity<RegistActivity>()
        }
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            loginMsg.observe(this@LoginActivity, Observer {
                if (it == "00") {
                    PreferencesUtil.saveValue("phone", et_phone.text.toString())
                    PreferencesUtil.saveValue("pwd", et_pass.text.toString())
                    toast("登录成功")
                    ARouter.getInstance().build(ARouterConfig.WKYK_MOUDLE).navigation()
                    finish()
                } else {
                    toast(it)
                }
            })
            loading.observe(this@LoginActivity, Observer {
                if (it) showProgressDialog(R.string.loading) else dismissProgressDialog()
            })
        }
    }

}