package com.example.ineedpizzabeer.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

// This handles issues with Low RAM devices for any images you may want to load.
@GlideModule
class TestGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // Prevent OOMs wherever possible
        if (getRamSize(context) <= 2048) {
            builder.setDefaultRequestOptions(
                RequestOptions()
                    .format(DecodeFormat.PREFER_RGB_565)
            )
        } else {
            builder.setDefaultRequestOptions(
                RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888)
            )
        }
    }
}
