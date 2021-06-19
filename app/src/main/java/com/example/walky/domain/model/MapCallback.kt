package com.example.walky.domain.model

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker

/**
 * OnMapReadyCallback,GoogleMap.OnMakerClickListenerを定義しているクラス
 */

class MapCallback(appActivity: AppCompatActivity): OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap
    private val activity: AppCompatActivity

    init {
        activity = appActivity
    }

    /**
     * OnMapReadyCallbackインターフェースからの継承
     * Mapが使用可能になったら、GPSクラスに現在地の使用を求める
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        val gps = GPS(activity)
        gps.enableCurrentLocation(mMap)
    }

    /**
     * GoogleMap.OnMakerClickListenerインターフェースからの継承
     * マーカーをクリックかタップした時に実行される
     */
    override fun onMarkerClick(p0: Marker) = false
}