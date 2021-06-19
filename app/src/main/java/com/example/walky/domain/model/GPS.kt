package com.example.walky.domain.model

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
import com.google.android.gms.maps.model.MarkerOptions

class GPS(appActivity: AppCompatActivity) {
    private val activity: AppCompatActivity
    private lateinit var lastLocation: Location
    private  val fusedLocationClient: FusedLocationProviderClient

    companion object {
        private  const val LOCATION_REQUEST_CODE = 1
    }

    init {
        activity = appActivity
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    fun enableCurrentLocation(mMap: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                GPS.LOCATION_REQUEST_CODE
            )

            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(activity){location ->

            if(location != null){
                lastLocation = location
                val  currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong, mMap)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
            }

        }
    }

    private fun checkPermission() {}

    private fun placeMarkerOnMap(currentLatLong: LatLng, mMap: GoogleMap) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("動作テスト1")
        mMap.addMarker(markerOptions)
    }
}