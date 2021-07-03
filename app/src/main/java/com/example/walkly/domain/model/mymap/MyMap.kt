package com.example.walkly.domain.model.mymap

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest

/**
 * 様々な処理に必要なGoogleMapクラスを管理している
 */
class MyMap(private val mMap: GoogleMap) {
    private val markerList: MarkerList = MarkerList()

    // TODO: 後で消す
    fun getMyMap(): GoogleMap {
        return mMap
    }

    /**
     * チェックポイントとなるマーカーを設置する
     *
     * @param point
     */
    fun addMarker(point: PointOfInterest) {
        if (!markerList.checkDuplicate(point.name)) {
            val markerOptions = MarkerOptions().position(point.latLng)
            markerOptions.title(point.name)
            val marker = mMap.addMarker(markerOptions)

            markerList.add(marker)
        }
    }
}