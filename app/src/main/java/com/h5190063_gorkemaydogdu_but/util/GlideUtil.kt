package com.h5190063_gorkemaydogdu_but.util


import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideUtil {

    fun ImageView.resimCek(url: String) {
        Glide.with(this.context)
                .load(url)
                .into(this)
    }
}