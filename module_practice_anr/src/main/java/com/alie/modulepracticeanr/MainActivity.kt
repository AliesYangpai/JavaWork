package com.alie.modulepracticeanr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtn1.setOnClickListener {
            // Let main thread sleep 20 sec and see what happens
            Thread.sleep(20 * 1000)
        }
    }
}
