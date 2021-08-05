package com.monebac.com.coroutines

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.monebac.com.R
import com.monebac.com.coroutines.bean.User
import com.monebac.common_base.base.mBaseActivity
import com.monebac.common_base.utils.onClickBind
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : mBaseActivity(), View.OnClickListener {
    lateinit var mainViewModel: MainViewModel

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel.flag.observe(this, Observer {
            Log.e("wawawawawawawawawa", "initData: $it")
        })
        onClickBind(this, btn1, btn2,btn3)
    }

    override fun onClick(v: View) {
        when (v) {
            btn1 -> {
                getMsg()
            }
            btn2 -> {
                mainViewModel.checkMsg()
                Log.e("qwq", "不阻塞")
            }
            btn3 ->{
                douGetMsg()
            }
        }
    }


    /**
     * 两个请求都完成 刷新界面
     */

    fun douGetMsg(){
        val job = GlobalScope.launch(Dispatchers.Main) {
            val async = async { mainViewModel.searchFromNet() }
            val async1 = async { mainViewModel.searchFromNet1() }

            val c = async.await()
            val b = async1.await()
            Log.e("douGetMsg", "douGetMsg: ?$c$b")
        }

        //TODO 代替上面得
//        MainScope().launch {
//
//        }
        jobs.add(job)

    }
    //页面销毁得时候 取消
    private val jobs = ArrayList<Job>()
    override fun onDestroy() {
        jobs.forEach {
            it.cancel()
        }
        super.onDestroy()
    }

    /**
     * runBlocking 启动一个新的协程并阻塞调用他的线程
     * launch和async都是启动一个协程但不会阻塞调用线程
     *
     * runBlocking 默认返回是一个该协程作业的当前状态
     *
     *
     *
     * launch 创建协程
     * async 创建带返回值的协程，返回的是 Deferred 类
     */
    private fun getMsg() {
        val runBlocking = runBlocking {
            Log.d("runBlocking", "启动一个协程")
        }
        Log.d("runBlocking", "$runBlocking")

        val launch = GlobalScope.launch {
            Log.d("launch", "启动一个协程")
        }
        Log.d("launchJob", "$launch")

        val async = GlobalScope.async {
            Log.d("async", "启动一个协程")
        }
        //

        Log.d("asyncJob", "$async")
    }


    /**
     * Dispatchers 调度器
     * Default：默认调度器，CPU密集型任务调度器，适合处理后台计算。通常处理一些单纯的计算任务，或者执行时间较短任务。比如：Json的解析，数据计算等
     * IO：IO调度器，，IO密集型任务调度器，适合执行IO相关操作。比如：网络处理，数据库操作，文件操作等
     * Main：UI调度器， 即在主线程上执行，通常用于UI交互，刷新等
     * Unconfined：非受限调度器，又或者称为“无所谓”调度器，不要求协程执行在特定线程上。
     *
     * MainScope、lifecycleScope、viewModelScope  都是使用的main调度器
     */
    private fun msg1() {
        GlobalScope.launch(Dispatchers.Default) { }
        GlobalScope.launch(Dispatchers.Main) { }
        GlobalScope.launch(Dispatchers.IO) { }
        GlobalScope.launch(Dispatchers.Unconfined) { }
    }


    fun msg2() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                //网络请求
            }
            // 更新ui
        }
    }








    fun feetchUsers():List<User>{
        return listOf()
    }
    fun feetUser():strList{
        return listOf()
    }
}

//typealias 定义别名
typealias strList = List<User>