package com.example.walky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.walky.application.MapApplicationService
import com.example.walky.ui.MapCallback

import com.google.android.gms.maps.SupportMapFragment

class MapsActivity : AppCompatActivity() {

    private val mapApplication: MapApplicationService = MapApplicationService(this)
    private val mapCallback: MapCallback = MapCallback(mapApplication)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        /**
         * TODO: GPSクラス修正
         * TODO: KDoc作成
         * TODO: 設計の修正
         */

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(mapCallback)
    }

}
