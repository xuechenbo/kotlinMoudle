package com.monebac.common_base.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import org.jetbrains.anko.toast

abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity() {

    protected open lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
        initViewModel()
        observe()
        initView()
        initData()
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
//        mViewModel = ViewModelProvider(this)[viewModelClass()]
        mViewModel = ViewModelProviders.of(this).get(viewModelClass())
    }

    protected abstract fun viewModelClass(): Class<VM>

    abstract fun initView()

    abstract fun initData()

    open fun observe() {
        mViewModel.tipString.observe(this, Observer {
            if (it == "00") "" else toast(it)

        })
    }

    protected fun setCustomToolBar(
            isVisibleLeftIcon: Boolean = false,
            toolbarTitle: String = ""
    ) {

    }
}
