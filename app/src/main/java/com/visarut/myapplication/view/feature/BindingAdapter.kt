package com.visarut.myapplication.view.feature

import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter



@BindingAdapter("imageUrl")
fun bindImageUrl(view : WebView, url: String?){
    url?.let{
        view.loadUrl(url)
    }
}