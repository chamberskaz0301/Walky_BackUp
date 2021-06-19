package com.example.walky.application

import androidx.appcompat.app.AppCompatActivity
import com.example.walky.domain.model.MapCallback

/**
 * 現在地の取得やアクティビティの開始などの指示を統括している
 */

class MapApplicationService(appActivity: AppCompatActivity) {
    private val activity: AppCompatActivity

    init {
        activity = appActivity
    }

    /**
     * Map表示準備完了時のコールバッククラスを返す
     */
    fun startUp(): MapCallback {
        val map = MapCallback(activity)
        return map
    }

}