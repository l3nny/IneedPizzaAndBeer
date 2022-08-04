package com.example.ineedpizzabeer.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context

@SuppressLint("ServiceCast")
fun getRamSize(context: Context): Long {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val memInfo = ActivityManager.MemoryInfo()
    activityManager.getMemoryInfo(memInfo)
    return (memInfo.totalMem) / 1000000
}