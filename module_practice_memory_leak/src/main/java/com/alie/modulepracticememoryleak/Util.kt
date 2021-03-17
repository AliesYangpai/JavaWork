package com.alie.modulepracticememoryleak

import android.content.Context

class Util {
    private var mContext:Context? = null

    companion object {
        @Volatile
        private var mInstance:Util? = null
        fun getInstance():Util {
            return mInstance ?: synchronized(this) {
                mInstance ?: Util().also {
                    mInstance = it
                }
            }
        }
    }

    fun doRegister(context: Context?) {
        mContext =context
    }

    fun doUnRegister() {
        mContext = null
    }

}