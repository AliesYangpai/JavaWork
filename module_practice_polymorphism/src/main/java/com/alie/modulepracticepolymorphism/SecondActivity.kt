package com.alie.modulepracticepolymorphism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alie.modulepracticepolymorphism.bean.kt.BasePlayer
import com.alie.modulepracticepolymorphism.bean.kt.Player
import com.alie.modulepracticepolymorphism.bean.kt.SoccerPlayer
import com.alie.modulepracticepolymorphism.bean.kt.TennisPlayer

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        startMethod()
    }
    private fun executeFun01(player:Player?) {
        player?.showCommonInfo()
    }

    fun executeFun02(basePlayer: BasePlayer?) {
        basePlayer?.let {
            it.showCommonInfo()
            it.showPlayerInfo()
        }
    }

    private fun doTest01() {
        println("===doTest01")
        executeFun01(SoccerPlayer("Cristiano"))
    }

    private fun doTest02() {
        println("===doTest02")
        executeFun02(TennisPlayer())
    }
    private fun startMethod() {
        doTest01() //【practice】common method
        doTest02() //【practice】abstract method (is similar to virtual method in c++)
    }
}