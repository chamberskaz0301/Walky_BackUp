package com.example.walkly.application

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.walkly.domain.model.GPS
import com.example.walkly.domain.model.mymap.MyMap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PointOfInterest

/**
 * 現在地の取得やアクティビティの開始などの指示を統括している
 */

class MapApplicationService(private val activity: AppCompatActivity) {
    private lateinit var myMap: MyMap

    /**
     * マップの準備ができたら現在地を取得し、GoogleMapを保管する
     *
     * @param mMap OnMapReadyCallbackインターフェースのonMapReadyが受け取る引数
     */
    fun startUp(mMap: GoogleMap) {
        val gps = GPS(activity)
        gps.enableCurrentLocation(mMap)
        myMap = MyMap(mMap)
    }

    /**
     * アクティビティを実行中ならマーカーを設置して、保存する(削除やルート用)
     * そうでなければ情報ウインドウを設置する
     *
     * @param point
     */
    fun handlePointClick(point: PointOfInterest) {

        // TODO: アクティビティ中ならtrue
        if (true) {
            myMap.addMarker(point)
        } else {
            Toast.makeText(
                activity,
                """
            ${point.name}
            緯度:${point.latLng.latitude}
            経度:${point.latLng.longitude}
            """,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun handleMarkerClick(marker: Marker) {
    }

}