package com.example.ineedpizzabeer.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

const val PERMISSIONS_REQUEST_LOCATION = 99


fun isLocationPermissionGranted(activity: Activity): Boolean {
    return if (ActivityCompat.checkSelfPermission(
            activity,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            activity,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSIONS_REQUEST_LOCATION
        )
        false
    } else {
        true
    }
}