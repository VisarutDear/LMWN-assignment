package com.visarut.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.Build




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
//        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
//            val v = this.window.decorView
//            v.systemUiVisibility = View.GONE
//        } else if (Build.VERSION.SDK_INT >= 19) {
//            //for new api versions.
//            val decorView = window.decorView
//            val uiOptions =
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            decorView.systemUiVisibility = uiOptions
//        }
    }
}