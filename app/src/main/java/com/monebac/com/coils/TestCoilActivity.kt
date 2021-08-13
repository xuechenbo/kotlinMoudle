package com.monebac.com.coils

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.monebac.com.R
import com.monebac.common_base.utils.onClickBind
import kotlinx.android.synthetic.main.act_coils.*

/**
 *   created by xxx
 *   on 2021/8/12
 */
class TestCoilActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_coils)

        onClickBind(this, bt1)
//        mImage.load(R.drawable.ic_launcher_background)


    }

    override fun onClick(v: View?) {
        when (v) {
            bt1 -> {


            }
        }
    }


}