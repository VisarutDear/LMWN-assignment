package com.visarut.myapplication

import android.app.Application
import com.visarut.myapplication.data.di.dataModule
import com.visarut.myapplication.data.di.retrofitModule
import com.visarut.myapplication.domain.di.useCaseModule
import com.visarut.myapplication.view.feature.featureHome.di.featureHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(retrofitModule, dataModule, featureHomeModule, useCaseModule))
        }
    }
}