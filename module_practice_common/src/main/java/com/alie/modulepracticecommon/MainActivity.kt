package com.alie.modulepracticecommon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.alie.modulepracticecommon.util.AvoidDoubleClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startMethod()
    }

    /**
     * fast click test
     */
    private fun doTest01() {
        println("===doTest01")
        findViewById<Button>(R.id.btn1).setOnClickListener(object : AvoidDoubleClickListener() {
            override fun onViewClick(v: View?) {
                println("===onViewClick go go go")
            }
        })
    }

    private fun startMethod() {
        doTest01()//【fast click】 fast click test
    }


}