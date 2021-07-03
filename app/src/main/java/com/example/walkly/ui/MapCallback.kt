package com.example.walkly.ui

import android.annotation.SuppressLint
import com.example.walkly.application.MapApplicationService
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PointOfInterest

/**
 *
 * Map準備完了時,マーカークリック時などの処理を定義しているクラス
 */

class MapCallback(private val mapApplication: MapApplicationService): OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnPoiClickListener {

    /**
     * OnMapReadyCallbackインターフェースからの継承
     * 準備ができたらGoogleMapを受け取り、アプリケーション立ち上げ
     *
     * @param googleMap GoogleMap SDK のメインクラス
     */
    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setOnMarkerClickListener(this)
        googleMap.setOnPoiClickListener(this)

        mapApplication.startUp(googleMap)
    }

    /**
     * Google.OnPoiClickListenerからの継承
     * スポットがクリックされた時に実行される
     */
    override fun onPoiClick(point: PointOfInterest) {
        mapApplication.handlePointClick(point)
    }

    /**
     * GoogleMap.OnMakerClickListenerインターフェースからの継承
     * マーカーをクリックかタップした時に実行される
     */
    override fun onMarkerClick(marker: Marker): Boolean {
        mapApplication.handleMarkerClick(marker)
        return false
    }
}