package com.monebac.com.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.monebac.com.R
import kotlinx.android.synthetic.main.act_recyclerlist.*

/**
 *   created by xxx
 *   on 2021/8/13
 */
class MyExpendListActivity : AppCompatActivity() {

    var mList = ArrayList<MultiItemEntity>()
    private lateinit var beginClassAdapter: BeginClassAdapter
    private var mSelectClassId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_recyclerlist)
        initData()
    }

    fun initData() {

        beginClassAdapter = BeginClassAdapter(getMsg(mList), this)
        beginClassAdapter.bindToRecyclerView(recyclerView)
        beginClassAdapter.expand(0)

        beginClassAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.con_Item, R.id.tv_titleName -> {
                    val data1 = adapter.data
                    if ((data1[position] as Course2Bean).campStageContentId == mSelectClassId) {
                        return@setOnItemChildClickListener
                    }
                    var i = 0
                    while (i < adapter.data.size) {
                        if (data1[i] is Course2Bean) {
                            (data1[i] as Course2Bean).isSelect = false
                        }
                        i++
                    }
                    (data1[position] as Course2Bean).isSelect = true
                    mSelectClassId = (data1[position] as Course2Bean).campStageContentId
                    beginClassAdapter.notifyDataSetChanged()
                }
                R.id.linItem -> {
                    val data = adapter.data
                    if ((data[position] as Course1Bean).isExpanded) {
                        beginClassAdapter.collapse(position)
                    } else {
                        beginClassAdapter.expand(position)
                    }
                    var i = 0
                    while (i < data.size) {
                        if (data[i] is Course2Bean) {
                            if ((data[i] as Course2Bean).campStageContentId !== mSelectClassId) {
                                (data[i] as Course2Bean).isSelect = false
                            }
                        }
                        i++
                    }
                    beginClassAdapter.notifyDataSetChanged()
                }
            }

        }
    }

    fun getMsg(mList: ArrayList<MultiItemEntity>): ArrayList<MultiItemEntity> {
        for (i in 0..5) {
            val course1Bean = Course1Bean(i)
            for (a in 0..3) {
                val course2Bean = Course2Bean()
                if (a == 0) {
                    course2Bean.isSelect = true
                }
                course2Bean.campStageContentId = i * 100 + a
                course1Bean.addSubItem(course2Bean)
            }
            mList.add(course1Bean)
        }
        return mList
    }


}