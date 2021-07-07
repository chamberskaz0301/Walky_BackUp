package com.example.walkly.domain.model

/**
 * 目的地のマーカー表示
 */
class Activity {
    private var isActivity: Boolean = false

    fun toggleIsActivity(){
        isActivity = !isActivity
    }
    fun getIsActivity(): Boolean {
        return isActivity
    }
}