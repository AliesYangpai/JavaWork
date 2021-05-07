package com.alie.modulepracticepolymorphism.bean.kt

open class Player(var mName: String = "") {

    open fun showCommonInfo() {
        println("===Player showCommonInfo")
    }
}