package com.example.walkly.domain.model

/*
* 天候予測の実行、取得
* 取得した文字データを画像に変換
*/

import android.R
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;


/*変数の初期化*/
var weatherName = ""


class Weather() {

    /*
    * 天候予測の実行、取得
    * APIキー(c0017427d17d19299b0b73042052f372)
    *
    * AndroidManifest.xmlに
    * <uses-permission android:name="android.permission.INTERNET" />
    * <application
    * android:usesCleartextTraffic="true"
    * を記述する必要あり？
    */

    public fun start() {
        println("処理を開始")

        /*APIキー*/
        val APIKEY = "c0017427d17d19299b0b73042052f372"

        /*変数の初期化*/
        var lat = ""
        var lon = ""

        /*緯度経度情報の取得*/
        Coordinate()


        /*緯度経度の設定*/
        var placeLat = lat
        var placeLon = lon

        /*検索結果→変数に格納*/
        var result = ""

        /*接続先URL*/
        var APIurl = "http://api.openweathermap.org/data/2.5/weather?" +
                "lat=" + placeLat + "&" +
                "lon=" + placeLon + "&" +
                "&APPID=" + APIKEY
        //http://api.openweathermap.org/data/2.5/weather?lat=&lon=&APPID=c0017427d17d19299b0b73042052f372
        //名古屋駅 http://api.openweathermap.org/data/2.5/weather?lat=35.156065&lon=136.916202&APPID=c0017427d17d19299b0b73042052f372

        var url = URL(APIurl)

        /*APIから情報を取得する*/
        var br = BufferedReader(InputStreamReader(url.openStream()))

        /*所得した情報を文字列化*/
        var str = br.readText()

        /*json形式のデータとして識別*/
        var json = JSONObject(str)

        /*weatherキーに対応するvalueを表示*/
        var weatherName = json.get("weather")

        println(weatherName)


        createWeatherImage(json)


    }
}


    private fun Coordinate() {
        /*
        * コピペ
        * https://note.com/c_omachi/n/nef100a0a23ad
        * */
        class MainActivity : AppCompatActivity(), LocationListener {
            private var manager: LocationManager? = null
            private var textView: TextView? = null
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                textView = findViewById<View>(R.id.textView) as TextView
                manager = getSystemService<Any>(Context.LOCATION_SERVICE) as LocationManager
            }

            override fun onResume() {
                super.onResume()
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        1
                    )
                    return
                }
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, this)
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, this)
            }

            override fun onStop() {
                super.onStop()
                if (manager != null) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    manager.removeUpdates(this)
                }
            }

            fun onLocationChanged(location: Location) {
                val text =
                    "緯度：" + location.getLatitude().toString() + "経度：" + location.getLongitude()
                textView!!.text = text

                var lat = location.getLatitude().toString()
                var lon = location.getLongitude()
            }

            fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            fun onProviderEnabled(provider: String?) {}
            fun onProviderDisabled(provider: String?) {}
        }
    }


    /*
    * 取得した文字データを画像に変換
    */
    private fun createWeatherImage(json: Any) {
        println("天気を画像データに変換")
        var weathericon = weatherName

    /*文字データを画像データ化*/
        /*案１*/
        var iconurl = ""

        when(weathericon){
            "01d" -> iconurl = "アイコンのURLとかパスとか clear sky"
            "02d" -> iconurl = "アイコンのURLとかパスとか few clouds"
            "03d" -> iconurl = "アイコンのURLとかパスとか scattered clouds"
            "04d" -> iconurl = "アイコンのURLとかパスとか broken clouds"
            "09d" -> iconurl = "アイコンのURLとかパスとか shower rain"
            "10d" -> iconurl = "アイコンのURLとかパスとか rain"
            "11d" -> iconurl = "アイコンのURLとかパスとか thunderstorm"
            "13d" -> iconurl = "アイコンのURLとかパスとか snow"
            "50d" -> iconurl = "アイコンのURLとかパスとか mist"

            "01n" -> iconurl = "アイコンのURLとかパスとか clear sky"
            "02n" -> iconurl = "アイコンのURLとかパスとか few clouds"
            "03n" -> iconurl = "アイコンのURLとかパスとか scattered clouds"
            "04n" -> iconurl = "アイコンのURLとかパスとか broken clouds"
            "09n" -> iconurl = "アイコンのURLとかパスとか shower rain"
            "10n" -> iconurl = "アイコンのURLとかパスとか rain"
            "11n" -> iconurl = "アイコンのURLとかパスとか thunderstorm"
            "13n" -> iconurl = "アイコンのURLとかパスとか snow"
            "50n" -> iconurl = "アイコンのURLとかパスとか mist"
        }
        /*案２*/
        if (weathericon == "") {
            var iconurl = "画像のパスとか？？/[画像データなし].png"
        }else {
            var iconurl = "画像のパスとか？？/" + weathericon + ".png"
        }
        /*画面上に画像を表示するスペースを作っておき、iconurlの値によって、動的に画像の種類が変わるようにする。*/

    }
