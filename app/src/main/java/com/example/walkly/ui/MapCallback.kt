package com.example.walkly.ui

import com.example.walkly.application.MapApplicationService
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker

/**
 *
 * Map準備完了時,マーカークリック時などの処理を定義しているクラス
 */

class MapCallback(private val mapApplication: MapApplicationService): OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    /**
     * OnMapReadyCallbackインターフェースからの継承
     * 準備ができたらGoogleMapを受け取り、アプリケーション立ち上げ
     */
    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMarkerClickListener(this)

        mapApplication.startUp(googleMap)
    }

    /**
     * GoogleMap.OnMakerClickListenerインターフェースからの継承
     * マーカーをクリックかタップした時に実行される
     */
    override fun onMarkerClick(p0: Marker) = false
}