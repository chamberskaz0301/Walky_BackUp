package com.example.walkly.lib

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.walkly.domain.model.MyApplication

/**
 *  HTTPリクエストを送る
 */
class HTTPRequest {

    /**
     * 引数urlにHTTPリクエストを送る
     *
     * @param url 送信先のURL
     * @param listener 成功時の処理
     * @param errorListener 失敗時の処理
     */
    fun getRequest(
        url: String,
        listener: Response.Listener<String>,
        errorListener: Response.ErrorListener,
    ) {
        val request =
            object: StringRequest(Method.GET, url, listener, errorListener){}
        val requestQueue = Volley.newRequestQueue(MyApplication.getContext())
        requestQueue.add(request)
    }
}