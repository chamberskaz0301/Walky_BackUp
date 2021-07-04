package com.example.walkly.application

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.example.walkly.R
import com.example.walkly.domain.model.GPS
import com.example.walkly.domain.model.MyApplication
import com.example.walkly.domain.model.mymap.MyMap
import com.example.walkly.domain.model.mymap.Route
import com.example.walkly.lib.HTTPRequest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest
import org.json.JSONObject

/**
 * 現在地の取得やアクティビティの開始などの指示を統括している
 */

class MapApplicationService(private val activity: AppCompatActivity) {
    private lateinit var myMap: MyMap
    private lateinit var route: Route
    /**
     * マップの準備ができたら現在地を取得し、GoogleMapを保管する
     *
     * @param mMap OnMapReadyCallbackインターフェースのonMapReadyが受け取る引数
     */
    fun startUp(mMap: GoogleMap) {
        val gps = GPS(activity)
        gps.enableCurrentLocation(mMap)
        myMap = MyMap(mMap)
        route = Route(mMap)


        // 天気予報
        if (false) {
            val url =
                "http://api.openweathermap.org/data/2.5/weather?lat=35.156065&lon=136.916202&APPID=c0017427d17d19299b0b73042052f372"
            val listener = Response.Listener<String> { response ->
                Log.d("debug", response)
                val jsonResponse = JSONObject(response) // 文字列をJSON形式に変換
                val weather = jsonResponse.getJSONArray("weather") // weather配列取得
                val item = weather.getJSONObject(0) // オブジェクト取得
                Log.d("weather", item.getString("main"))
                Log.d("icon", item.getString("icon"))
            }
            val errorListener = Response.ErrorListener { }
            HTTPRequest().getRequest(url, listener, errorListener)
        }

        // 周辺施設
        if (false) {
            val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=35.1681694,136.8857641&radius=5000&type=restaurant&key=${MyApplication.getContext().getString(
                R.string.google_maps_key
            )}"
            val listener = Response.Listener<String> { response ->
                val jsonResponse = JSONObject(response) // 文字列をJSON形式に変換
                val results = jsonResponse.getJSONArray("results") // results:配列取得
                for (i in 0 until results.length()) {
                    val item = results.getJSONObject(i) // i:オブジェクト取得
                    val geometry = item.getJSONObject("geometry") // geometry:オブジェクト取得
                    val location = geometry.getJSONObject("location") // location:オブジェクト取得
                    Log.d("lat", location.getInt("lat").toString()) // 数値のlat:を文字列に変換して表示 実際の利用時は変換不要
                    Log.d("lng", location.getInt("lng").toString()) // 数値のlng:を文字列に変換して表示 実際の利用時は変換不要
                }
            }
            val errorListener = Response.ErrorListener {  }
            HTTPRequest().getRequest(url, listener, errorListener)
        }
    }

    /**
     * アクティビティの開始
     */
    fun handleActivityButton() {
        // TODO: アクティビティの開始状況により処理を切り替える
        // TODO: 既存ルートを消す
        // TODO: 現在地を取得する
        // TODO: チェックポイントの位置を取得する

        val mMap = myMap.getMyMap()
        val origin = LatLng(35.1681, 136.8856) // HAL

        val place: MutableList<LatLng> = ArrayList()
        place.add(LatLng(35.1709, 136.8815)) // 名古屋駅
        place.add(LatLng(35.1700, 136.8852)) // ミッドランド
        place.add(LatLng(35.1716, 136.8863)) // ユニモール

        mMap.addMarker(MarkerOptions().position(origin))
        for (j in 0 until place.size) {
            mMap.addMarker(MarkerOptions().position(place[j]))
        }

        route.drawRoute(origin, place)
    }

    /**
     * アクティビティを実行中ならマーカーを設置して、保存する(削除やルート用)
     * そうでなければ情報ウインドウを設置する
     *
     * @param point
     */
    fun handlePointClick(point: PointOfInterest) {

        // TODO: アクティビティ中ならtrue
        if (true) {
            myMap.addMarker(point)
        } else {
            Toast.makeText(
                activity,
                """
            ${point.name}
            緯度:${point.latLng.latitude}
            経度:${point.latLng.longitude}
            """,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun handleMarkerClick(marker: Marker) {
    }
}