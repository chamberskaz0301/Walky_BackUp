package com.example.walkly.lib

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * パーミッションに関する機能をまとめるクラス
 *
 * @property activity メインアクティビティ
 */
class Permission(private val activity: AppCompatActivity) {

    /**
     * パーミッションの権限を確認する
     *
     * @param permission Manifest.permission.???
     * @return 許可されていたらtrue
     */
    fun checkPermission(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(
            activity,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * パーミッションの権限を求める
     *
     * @param permission Manifest.permission.???
     * @param request_code res/permission.xml内の数字
     */
    fun requestPermission(permission: String, request_code: Int) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(permission),
            request_code
        )
    }
}