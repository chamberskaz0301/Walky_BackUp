package com.example.walkly.lib

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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
     * @param context
     *
     * TODO: contextの修正
     */
    fun getRequest(
        url: String,
        listener: Response.Listener<String>,
        errorListener: Response.ErrorListener,
        context: Context
    ) {
        val request =
            object: StringRequest(Method.GET, url, listener, errorListener){}
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(request)
    }
}