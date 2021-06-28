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
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

    public fun start(latLng: LatLng) {
        println("処理を開始")

        /*CoroutineScope*/
        CoroutineScope(Dispatchers.Main).launch {
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
}

        /*
        * コピペ
        * https://note.com/c_omachi/n/nef100a0a23ad
        * */



    /*
    * 取得した文字データを画像に変換
    */
    private fun createWeatherImage(json: Any) {
        println("天気を画像データに変換")
        var weathericon = weatherName
        var iconurl : String

        if (weathericon == "") {
            iconurl = "[画像データなし].png"
        }else {
            iconurl = weathericon + ".png"
        }

        println(iconurl)
        /*画面上に画像を表示するスペースを作っておき、iconurlの値によって、動的に画像の種類が変わるようにする。*/

    }
