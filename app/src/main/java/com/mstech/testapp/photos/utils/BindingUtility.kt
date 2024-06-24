package com.mstech.testapp.photos.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mstech.testapp.R
import com.squareup.picasso.Picasso

object BindingUtils {
    @JvmStatic
    @BindingAdapter("thumbnailUrl")
    fun loadThumbnailImage(imageView: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .into(imageView)
        }
    }


    @JvmStatic
    @BindingAdapter("url")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .into(imageView)
        }
    }
}

