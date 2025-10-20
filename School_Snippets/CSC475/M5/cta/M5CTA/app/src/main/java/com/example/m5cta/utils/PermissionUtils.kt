package com.example.m5cta.utils

import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat

object PermissionUtils {
    fun getCameraPermission(): String = android.Manifest.permission.CAMERA

    fun getReadImagePermission(): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        android.Manifest.permission.READ_MEDIA_IMAGES
    } else {
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    }

    fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}