package com.alie.modulepracticecommon

import android.app.Application


class CommonAppication : Application() {
    companion object {
        private var mInstance: CommonAppication? = null
        fun getApplication():CommonAppication = mInstance?: synchronized(this){
            mInstance?: CommonAppication().also {
                mInstance = it
            }
        }
    }


    override fun onCreate() {
        super.onCreate()
        mInstance = this;
    }
}