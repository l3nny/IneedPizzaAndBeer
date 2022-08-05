package com.example.ineedpizzabeer.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ineedpizzabeer.R

private const val FILE_SCHEME = "file"

// Load callback provided if there is any task to perform once image is loaded or failed.
@SuppressLint("CheckResult")
fun ImageView.load(imageUrl: String) {

    if (imageUrl.isEmpty()) {
        GlideApp.with(context).load(setImageResource(R.mipmap.ic_launcher))
    } else {
        if (isLoadable()) {

            if (Uri.parse(imageUrl).scheme == FILE_SCHEME) {
                GlideApp.with(context).asGif().load(imageUrl)
            } else {
                GlideApp.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .skipMemoryCache(true)
                    .override(1500, 1500)
            }
                .into(this)
        }
    }
}

private fun ImageView.isLoadable() = if (context is Activity) {
    val activity = context as Activity
    (!activity.isFinishing && !activity.isDestroyed)
} else {
    true
}


