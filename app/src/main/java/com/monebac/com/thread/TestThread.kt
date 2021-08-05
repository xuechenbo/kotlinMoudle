package com.monebac.com.thread

import android.os.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.monebac.com.R
import com.monebac.common_base.Constant
import com.monebac.common_base.utils.onClickBind
import kotlinx.android.synthetic.main.act_thread.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * created by xxx
 * on 2021/8/4
 * 多线程实现
 * https://blog.csdn.net/qq_41648631/article/details/103045252
 */
internal class TestThread : AppCompatActivity(), View.OnClickListener {

    lateinit var handlerThread: HandlerThread
    lateinit var handler: Handler
    lateinit var myTast: MyTast
    lateinit var threadPoolExecutor: ThreadPoolExecutor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_thread)
        initData()
        onClickBind(this, btn1, btn2, btn3, btn4)

    }

    private fun initData() {


        handlerThread = HandlerThread("thread1")
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        myTast = MyTast()

    }

    //花花

    /**
     * handlerThread  子线程添加looper  和handler原理一样，子线程队列  在子线程没三秒执行一次
     */
    override fun onClick(v: View?) {
        when (v) {
            btn1 -> {
                handler.post {
                    Log.d("TAG", Thread.currentThread().id.toString())
                    SystemClock.sleep(3000);
                }
            }
            btn2 -> {
                var thread = Thread(runnable).run {
                    start()
                }
            }
            btn3 -> {
//                myTast.execute()
            }
            btn4 -> {
                //线程池
                operatThread(15)
            }
        }
    }

    /******************************* 线程池 ************************************/

    /**
     * 1，newFixedThreadPool()  返回固定数量的线程池，该线程池中的线程数量始终不变，可以控制线程的最大并发数
     * 2，newCachedThreadPool() 返回可以根据实际情况调整线程池中线程数量的线程池，线程数量不确定，是根据实际情况动态调整的
     * 3，newSingleThreadExecutor() 返回一个只用一个线程的线程池，即每次只执行一个线程任务，多余的任务会保存到一个任务队列中，等待这个线程空闲，然后再按照FIFO方式顺序执行任务队列中的任务
     * 4，newScheduledThreadPool() 返回一个可以控制线程池内线程定时或周期性执行某任务的线程池
     * 5，newSingleThreadScheduledExecutor() 返回一个可以控制线程池内线程定时或周期性执行某任务的线程池
     *
     */
    /**
     * 1 corePoolSize 核心线程的数量
     * 2 maximumPoolSize  线程池中最大线程数量
     * 3 keepAliveTime 非核心线程的超时时长，当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收。
     * 如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
     * 4 unit 第三个参数的单位
     * 5 workQueue 线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的
     *
     * threadPoolExecutor = ThreadPoolExecutor(5, 100, 5,TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>(100))
     */

    val newSingleThreadExecutor = Executors.newSingleThreadExecutor()
    var newFix = Executors.newFixedThreadPool(5)
    val newCachedThreadPool = Executors.newCachedThreadPool()
    var lock = ReentrantLock()
    var LOCK_WAIT = 86400


    private fun operatThread(vararg int: Int) {
        //new Thread()
//        thread {
//            var threads = arrayOfNulls<MyThread>(3)
//            for (i in 0 until 3) {
//                threads[i] = MyThread()
//                threads[i]?.start()
//            }
//            for (i in 0 until 3) {
//                val myThread = threads[i]
//                myThread?.join()
//            }
//        }

//        for (i in 1..int[0]) {
//            newFix.execute(Runnable {
//                Log.e("TAG", "执行结果  ${getMsg()}")
//            })
//        }

//        for (i in 0..15){
//            newSingleThreadExecutor.execute {
//                val threadName = Thread.currentThread().name
//                Log.e("TAG", "Single: $threadName 执行第$i 个任务"  )
//                Thread.sleep(2000);
//            }
//        }

//        for (i in 0 until 3) {
//            newCachedThreadPool.execute {
//                parseNum(i)
//            }
//        }

        NetUtils.getMsg()
    }


    private fun parseNum(i: Int) {
        Log.e("当前线程:", "${Thread.currentThread().name}")
        Thread.sleep(3000)
        Log.e("TAG", "执行结果  ${getMsg(i)}")
    }

    var totalNumA = 0
    var totalNumB = 0
    var totalNumC = 0
    private fun getMsg(num: Int): Int {
        var threadName = Thread.currentThread().name
        Log.e("TAG", threadName + "进来了")
        try {
            lock.tryLock(LOCK_WAIT.toLong(), TimeUnit.SECONDS)
            when (num) {
                0 -> {
                    totalNumA++
                }
                1 -> {
                    totalNumB++
                }
                2 -> {
                    totalNumC++
                }
            }
            Thread.sleep(8000)
            Log.e("TAG", "线程==$threadName  执行完毕")
        } finally {
            lock.unlock()
        }
        return when (num) {
            0 -> {
                totalNumA
            }
            1 -> {
                totalNumB
            }
            2 -> {
                totalNumC
            }
            else ->
                0
        }
    }

    class MyThread : Thread() {
        var lock = ReentrantLock(true)
        var LOCK_WAIT = 86400
        override fun run() {
            super.run()
            Log.e("TAG", "返回结果:${Constant.mainNum}------${getMsg()}")

        }

        private fun getMsg(): Int {
            var threadName = currentThread().name
            Log.e("TAG", threadName + "进来了")
            try {
                lock.tryLock(LOCK_WAIT.toLong(), TimeUnit.SECONDS)
                Constant.mainNum++
                sleep(5000)
                Log.e("TAG", "线程==$threadName  执行完毕")
            } finally {
                lock.unlock()
            }
            return Constant.mainNum
        }
    }


    /**********************************************************************************/


    /******************* Thread+Runnable***********************************/
    /**
     * 内部原理 = Thread类 + Handler类机制
     * 通过继承Thread类，快速地创建1个带有Looper对象的新工作线程\
     * 通过封装Handler类，快速创建Handler & 与其他线程进行通信
     */
    var mNum = 0
    private val runnable = object : Runnable {
        override fun run() {
            mNum++
            Log.e("TAG", "mNum:$mNum")
            if (mNum == 1000) {
                var message = Message()
                message.what = 100
//            mHandler.sendMessage(Message().apply {
//                what = 100
//            })
                mHandler.sendMessage(message)
            }
            mHandler.postDelayed(this, 1000)
        }
    }

    var mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
//                100 -> this.removeCallbacks(runnable)
            }
            super.handleMessage(msg)
        }
    }
    /******************************************************/


    /********************** AsyncTask *******************************/
    /**
     * 不与任何组件绑定生命周期
     *
     * 实现原理== 线程池 + Handler
     * 线程池用于线程调度、复用 & 执行任务；Handler 用于异步通信
     * 封装了两个线程池，一个handler  任务队列线程池，执行线程池，
     */
    class MyTast : AsyncTask<String, Integer, String>() {

        //执行 线程任务前的操作
        override fun onPreExecute() {
            Log.e("TAG", "onPreExecute: 初始化参数")
        }

        //接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        override fun doInBackground(vararg params: String?): String {

            return "null"
        }

        //在主线程 显示线程任务执行的进度
        override fun onProgressUpdate(vararg values: Integer?) {
            super.onProgressUpdate(*values)
        }

        //接收线程任务执行结果、将执行结果显示到UI组件
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }

        //设置取消状态
        override fun onCancelled() {
            super.onCancelled()

        }
    }
    /****************************************************************/

    /******************************* IntentService ************************************/


    /**********************************************************************************/


}