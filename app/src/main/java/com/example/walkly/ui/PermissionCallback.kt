package com.example.walkly.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class PermissionCallback(appActivity: AppCompatActivity) {
    private val activity: AppCompatActivity = appActivity

    companion object {
        private  const val LOCATION_REQUEST_CODE = 1
    }

    /**
     * TODO: このファイルそのものをPermissionクラスとする？
     * TODO: SplashActivityの処理をここに書く？
     */

    /**
     *  GPSが利用できるのかどうか
     * TODO: チェックしたいパーミッションを受け取るようにする?
     */
    fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * GPSの利用許可を求める
     * TODO: 同上
     * TODO: リクエストコードを外部ファイルにまとめる?
     */
    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_CODE
        )
    }
}