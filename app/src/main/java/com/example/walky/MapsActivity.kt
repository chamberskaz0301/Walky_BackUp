package com.example.walky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.walky.application.MapApplicationService

import com.google.android.gms.maps.SupportMapFragment

class MapsActivity : AppCompatActivity() {

    private val mapApplication: MapApplicationService = MapApplicationService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val map = mapApplication.startUp()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(map)
    }

}
