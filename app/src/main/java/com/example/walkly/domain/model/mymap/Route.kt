package com.example.walkly.domain.model.mymap

import android.graphics.Color
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.walkly.R
import com.example.walkly.domain.model.MyApplication
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import org.json.JSONObject

class Route(private val mMap: GoogleMap) {

    fun drawRoute() {
        // TODO:debug

        if (true) {

            mMap.addMarker(MarkerOptions().position(LatLng(35.1681, 136.8856))) // HAL
            mMap.addMarker(MarkerOptions().position(LatLng(35.1709, 136.8815))) // 名古屋駅

            mMap.addMarker(MarkerOptions().position(LatLng(35.1700, 136.8852))) // ミッドランド
            mMap.addMarker(MarkerOptions().position(LatLng(35.1716, 136.8863))) // ユニモール


            val path: MutableList<List<LatLng>> = ArrayList()
            val apiKey: String = MyApplication.getContext().getString(R.string.google_maps_key)
            val urlDirections =
                "https://maps.googleapis.com/maps/api/directions/json?mode=walking&origin=35.1681,136.8856&waypoints=35.1700,136.8852|35.1716,136.8863&destination=35.1709,136.8815&key=$apiKey"
            val directionsRequest =
                object : StringRequest(Method.GET, urlDirections, Response.Listener { response ->
                    val jsonResponse = JSONObject(response)
                    // Get routes
                    val routes = jsonResponse.getJSONArray("routes")
                    val legs = routes.getJSONObject(0).getJSONArray("legs")

                    for (j in 0 until legs.length()) {

                        val steps = legs.getJSONObject(j).getJSONArray("steps")
                        for (i in 0 until steps.length()) {
                            val points =
                                steps.getJSONObject(i).getJSONObject("polyline").getString("points")
                            path.add(PolyUtil.decode(points))
                        }
                        for (i in 0 until path.size) {
                            mMap.addPolyline(PolylineOptions().addAll(path[i]).color(Color.BLUE))
                        }

                    }

                }, Response.ErrorListener {
                }) {}
            val requestQueue = Volley.newRequestQueue(MyApplication.getContext())
            requestQueue.add(directionsRequest)
        }

    }

}