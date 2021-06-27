package com.example.walkly

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.walkly.lib.Permission
import com.example.walkly.ui.PermissionCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val permission: Permission = Permission(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            if (permission.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                val intent = Intent(this@SplashActivity, MapsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                permission.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, R.integer.location_request_code)
            }
        }
    }

    /**
     * リクエストコードが1なら、GPSパーミッションのコールバックメソッドを実行する
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionCallback = PermissionCallback(this)
        if (requestCode == R.integer.location_request_code) {
            permissionCallback.onLocationResultCallback()
        }
        val intent = Intent(this@SplashActivity, MapsActivity::class.java)
        startActivity(intent)
        finish()

    }
}