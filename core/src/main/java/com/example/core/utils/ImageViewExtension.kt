package com.example.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.core.R

fun ImageView.downloadImage(imageUrl: String) {
    Glide.with(context).load(imageUrl).error(R.drawable.img_error)
        .into(this)
}