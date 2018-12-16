package com.shutterstock.imggetter.android

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class ImgGetterApplication : Application() {
    companion object {
        fun getComponent(context: Context): MainComponent = (context.applicationContext as ImgGetterApplication).component
    }

    private lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerMainComponent.builder().mainModule(MainModule(this)).build()

        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
    }
}
