package com.example.walkly.domain.model.mymap

import android.graphics.Color
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.walkly.R
import com.example.walkly.domain.model.MyApplication
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import org.json.JSONObject

class Route(private val mMap: GoogleMap) {

    /**
     * 経路を引く
     *
     * @param origin 始点
     * @param place 中間地点,終点
     * @throws Exception APIキーが間違っているなどのエラー
     */
    fun drawRoute(origin: LatLng, place: MutableList<LatLng>) {

        // TODO: 正式リリース時に消す
        if (true) {
            // TODO: リファクタリング
            // TODO: HTTPリクエスト部の分離

            val path: MutableList<List<LatLng>> = ArrayList()
            val urlDirections = createURLDirections(origin, place)
            val directionsRequest =
                object : StringRequest(Method.GET, urlDirections, Response.Listener { response ->

                    val jsonResponse = JSONObject(response)
                    try {

                        val routes = jsonResponse.getJSONArray("routes")
                        val legs = routes.getJSONObject(0).getJSONArray("legs")

                        for (j in 0 until legs.length()) {

                            val steps = legs.getJSONObject(j).getJSONArray("steps")
                            for (i in 0 until steps.length()) {
                                val points =
                                    steps.getJSONObject(i).getJSONObject("polyline")
                                        .getString("points")
                                path.add(PolyUtil.decode(points))
                            }
                            for (i in 0 until path.size) {
                                mMap.addPolyline(
                                    PolylineOptions().addAll(path[i]).color(Color.BLUE)
                                )
                            }

                        }

                    } catch (e: Exception) {
                        throw Exception(jsonResponse.getString("error_message"))
                    }

                }, Response.ErrorListener {
                }) {}
            val requestQueue = Volley.newRequestQueue(MyApplication.getContext())
            requestQueue.add(directionsRequest)
        }

    }

    /**
     * 経路検索用URLの作成
     *
     * @param origin 経路の始点となる場所
     * @param points 中間地点、終点となる場所
     * @throws IllegalArgumentException 配列に要素が1つもない = 終点が存在しない
     */
    private fun createURLDirections(origin: LatLng, points: MutableList<LatLng>): String {
        val apiKey: String = MyApplication.getContext().getString(R.string.google_maps_key)
        val originParam = "origin=${origin.latitude},${origin.longitude}"

        val size = points.size
        if (size <= 0) {
            throw IllegalArgumentException("引数pointsには1つ以上の要素が必要です")
        }
        var pointsParam = "&destination="
        if (size == 1) {
            pointsParam += "${points[0].latitude},${points[0].longitude}"
        } else {
            var waypointsParam = "&waypoints=${points[0].latitude},${points[0].longitude}"
            for (i in 1 until size - 1) {
                waypointsParam += "|${points[i].latitude},${points[i].longitude}"
            }
             pointsParam += "${points[size - 1].latitude},${points[size - 1].longitude}${waypointsParam}"
        }

        return "https://maps.googleapis.com/maps/api/directions/json?mode=walking&${originParam}${pointsParam}&key=${apiKey}"
    }

}