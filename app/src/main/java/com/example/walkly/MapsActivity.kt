
package com.example.walkly


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.walkly.application.MapApplicationService
import com.example.walkly.domain.model.Weather
import com.example.walkly.ui.MapCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapsActivity : AppCompatActivity() {


    private val mapApplication: MapApplicationService = MapApplicationService(this)
    private val mapCallback: MapCallback = MapCallback(mapApplication)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        /**
         * TODO: GPSクラス修正
         *  - 許可しない時の処理
         * https://developer.android.com/training/permissions/requesting
         */

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(mapCallback)

    }

}
