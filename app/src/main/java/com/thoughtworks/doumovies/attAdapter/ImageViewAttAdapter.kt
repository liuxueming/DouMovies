package com.thoughtworks.doumovies.attAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thoughtworks.doumovies.R

object ImageViewAtrrAdapter {

    @JvmStatic
    @BindingAdapter("remote")
    fun loadImage(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_image_loading))
                .load(url).into(imageView)
        }
    }
}