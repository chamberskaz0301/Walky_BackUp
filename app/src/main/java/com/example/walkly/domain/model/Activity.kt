package com.example.walkly.domain.model

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

/**
 * 目的地のマーカー表示
 */

class Activity {

    /**
     * アクティビティの起動判定
     */
    private var activityJudgment: Boolean = false

    fun setActivityJudgement(flag: Boolean){
        activityJudgment = flag
    }
    fun getActivityJudgement(): Boolean {
        return activityJudgment
    }

    /**
     * アクティビティの開始
     */
    fun startActivity(mMap: GoogleMap) {

        // 座標取得処理
        val goalLocation = getGoalLocation()
        // マーカー仮表示用座標

    }

    /**
     * 目的地座標取得処理
     */
    private fun getGoalLocation(): LatLng {

        /**
         * 緯度経度受け取り
         */


        val goalLocation = LatLng(-33.87365, 151.20689)
        return goalLocation
    }


}