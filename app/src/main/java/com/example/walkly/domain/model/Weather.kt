package com.example.walkly.domain.model

/*
* 天候予測の実行、取得
* 取得した文字データを画像に変換
*/


import android.util.Log
import com.android.volley.Response
import com.example.walkly.lib.HTTPRequest
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject




open class Weather() {

    public fun start(latLng: LatLng) {
        println("処理を開始")

        /*変数の設定*/
        var item = ""
        var weathericon = ""
        var weathername = ""

        /*HTTPRequest01*/
        /*APIキー*/
        val APIKEY = "c0017427d17d19299b0b73042052f372"

        /*緯度経度の設定*/
        var placeLat = latLng.latitude
        var placeLon = latLng.longitude

        /*接続先URL*/
        var APIurl = "http://api.openweathermap.org/data/2.5/weather?" +
                "lat=" + placeLat + "&" +
                "lon=" + placeLon + "&" +
                "&APPID=" + APIKEY

        var url = APIurl

        /*HTTPRequest02*/
        val listener = Response.Listener<String> { response ->
            Log.d("debug", response)
            val jsonResponse = JSONObject(response)
            val weather = jsonResponse.getJSONArray("weather")
            val item = weather.getJSONObject(0)

            var weathername = item.getString("main")
            var weathericon = item.getString("icon")

            Log.d("weather", item.getString("main"))
            Log.d("icon", item.getString("icon"))

            /*取得した文字データを画像に変換*/
            println("天気を画像データに変換")
            println(weathericon)

            var iconurl : String

            if (weathericon == "") {
                iconurl = "[画像データなし].png"
            }else {
                iconurl = weathericon + ".png"
            }

            println(iconurl)
        }

        /*HTTPRequest03*/
        val errorListener = Response.ErrorListener {  }

        HTTPRequest().getRequest(url, listener, errorListener)



    }


}
