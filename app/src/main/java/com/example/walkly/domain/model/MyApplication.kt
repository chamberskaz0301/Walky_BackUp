package com.example.walkly.domain.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication: Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var mContext: Context

        fun getContext(): Context {
            return mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}