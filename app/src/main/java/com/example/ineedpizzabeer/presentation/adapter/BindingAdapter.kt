package com.example.ineedpizzabeer.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.ineedpizzabeer.utils.load

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("src")
    fun setImage(view: ImageView, url: String?) {
        if (url != null) {
            view.load(url)
        }
    }
}