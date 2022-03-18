package com.visarut.myapplication.view.feature

import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.visarut.myapplication.R


@BindingAdapter("imageUrl")
fun bindImageUrl(view : AppCompatImageView, url: String?){
    url?.let{
        view.loadSvg(url)
    }
}

fun AppCompatImageView.loadSvg(myUrl: String?, cache: Boolean = true, errorImg: Int = R.drawable.ic_arrow_up) {
    myUrl?.let {
        if (it.lowercase().endsWith("svg")) {
            val imageLoader = ImageLoader.Builder(this.context)
                .components {
                    add(SvgDecoder.Factory())
                }.build()

            val request = ImageRequest.Builder(this.context).apply {
                error(errorImg)
                placeholder(errorImg)
                data(it)
            }.target(this).build()

            imageLoader.enqueue(request)
        }
    }
}