package com.example.walkly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.walkly.ui.PermissionCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val permissionCallback: PermissionCallback = PermissionCallback(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            if (permissionCallback.checkLocationPermission()) {
                val intent = Intent(this@SplashActivity, MapsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                permissionCallback.requestLocationPermission()
            }
        }
    }

    /**
     * パーミッション拒否なら警告文を表示する
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!permissionCallback.checkLocationPermission()) {
            AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("一部機能が利用できません")
                .setPositiveButton("OK") { dialog, which -> }
                .show()
        }
        val intent = Intent(this@SplashActivity, MapsActivity::class.java)
        startActivity(intent)
        finish()
    }
}