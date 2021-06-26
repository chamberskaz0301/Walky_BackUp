package com.example.walkly.domain.model

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

/**
 * GPS利用の許可を求めたり、現在地レイヤーを表示したりする
 */

class GPS(appActivity: AppCompatActivity): ActivityCompat.OnRequestPermissionsResultCallback {
    private val activity: AppCompatActivity = appActivity
    private lateinit var lastLocation: Location
    private  val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

    companion object {
        private  const val LOCATION_REQUEST_CODE = 1
    }


    /**
     * GPS利用許可を求める
     * 現在地レイヤーの表示
     *
     * @param mMap GoogleMap SDK のメインクラス
     */
    fun enableCurrentLocation(mMap: GoogleMap) {
        mMap.isMyLocationEnabled = checkPermission()
        fusedLocationClient.lastLocation.addOnSuccessListener(activity){location ->
            if(location != null){
                lastLocation = location
                val  currentLatLong = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 18f))
            }
        }
    }

    /**
     * GPS利用許可を求める
     */
     fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE
            )
            return (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)

        }
        return true
    }

    // TODO: ここでは実行しない
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_REQUEST_CODE) {
            return
        }
        print(requestCode)
        print(permissions)
        print(grantResults)
    }
}