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


        /**
         * TODO: getMapAsyncのonMapReady実行を待ちたい = コールバック内のGPSクラス依存をなくしたい
         * TODO: MapCallbackのuiパッケージ化検討
         * TODO: GPSクラス修正
         * TODO: KDoc作成
         * TODO: 設計の修正
         */



        val map = mapApplication.startUp()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(map)
    }

}
