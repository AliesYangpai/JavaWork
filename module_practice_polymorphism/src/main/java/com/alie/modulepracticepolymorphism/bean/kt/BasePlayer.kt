package com.alie.modulepracticepolymorphism.bean.kt

public abstract class BasePlayer(var mName: String = "") {
   public open fun showCommonInfo() {
        println("===BasePlayer showCommonInfo()")
    }

    public abstract fun showPlayerInfo()
}