package com.example.walkly.domain.model

/*
* 天候予測の実行、取得
* 取得した文字データを画像に変換
*/

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import org.json.JSONObject
import java.util.*


class Weather() {

    private fun unixTimeChange(unixTime: String): String {
        var sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        var nowTime = Date(unixTime.toInt() * 1000L)
        return sdf.format(nowTime)
    }


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

            /*緯度経度の設定*/
            var placeLat = /*緯度*/null
            var placeLon = /*経度*/null
            /*検索結果→変数に格納*/
            var result = ""

            /*接続先URL*/
            var APIurl = "http://api.openweathermap.org/data/2.5/onecall?"+
                      "lat="+placeLat+"&"+
                      "lon="+placeLon+"&"+
                      "&APPID="+APIKEY




            var url = URL(APIurl)

            /*APIから情報を取得する*/
            var br = BufferedReader(InputStreamReader(url.openStream()))


            /*所得した情報を文字列化*/
            var str = br.readText()

            /*json形式のデータとして識別*/
            var json = JSONObject(str)

            /*hourlyの配列を取得*/
            var hourly = json.getJSONArray("hourly")

            /*天気予想の取得(iで時間指定)*/
            for (i in 1..2) {
                var firstObject = hourly.getJSONObject(i)
                var weatherList = firstObject.getJSONArray("weather").getJSONObject(0)
                // unixtime形式で保持されている時刻を取得
                var time = firstObject.getString("dt")
                // 天気を取得
                var descriptionText = weatherList.getString("description")
                result += "${unixTimeChange(time)}  $descriptionText \n\n"
            }

        Log.d("debug", result)


        createWeatherImage()
    }

    /*
    * 取得した文字データを画像に変換
    */
    private fun createWeatherImage(){
        println("天気を画像データに変換")
    }

}