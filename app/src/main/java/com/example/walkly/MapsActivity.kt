
package com.example.walkly


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.walkly.application.MapApplicationService
import com.example.walkly.ui.MapCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MapsActivity : AppCompatActivity() {


    private val mapApplication: MapApplicationService = MapApplicationService(this)
    private val mapCallback: MapCallback = MapCallback(mapApplication)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(mapCallback)



        // TODO: Context置き換え
        val button = findViewById<FloatingActionButton>(R.id.fab)
        button.setOnClickListener {
            mapApplication.handleActivityButton()
        }
    }

}
