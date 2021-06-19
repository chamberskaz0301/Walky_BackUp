package com.example.walky.domain.model

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker

/**
 *
 * Map準備完了時,マーカークリック時などの処理を定義しているクラス
 */

class MapCallback(private val gps: GPS): OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap

    /**
     * OnMapReadyCallbackインターフェースからの継承
     * Mapが使用可能になったら、GPSクラスに現在地の使用を求める
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        gps.enableCurrentLocation(mMap)
    }

    /**
     * GoogleMap.OnMakerClickListenerインターフェースからの継承
     * マーカーをクリックかタップした時に実行される
     */
    override fun onMarkerClick(p0: Marker) = false
}