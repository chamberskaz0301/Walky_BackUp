package com.example.walky.application

import androidx.appcompat.app.AppCompatActivity
import com.example.walky.domain.model.GPS
import com.example.walky.domain.model.MapCallback

/**
 * 現在地の取得やアクティビティの開始などの指示を統括している
 */

class MapApplicationService(private val activity: AppCompatActivity) {

    /**
     * Map表示準備完了時のコールバッククラスを返す
     */
    fun startUp(): MapCallback {
        val gps = GPS(activity)
        return MapCallback(gps)
    }

}