package com.alie.modulepracticememoryleak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MemoryLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_leak)
        Util.getInstance().doRegister(this)
    }

    override fun onDestroy() {
        /**
         * unbale this line to produce memory leak
         * enable this line to fix it up
         */
//        Util.getInstance().doUnRegister()
        super.onDestroy()
    }
}