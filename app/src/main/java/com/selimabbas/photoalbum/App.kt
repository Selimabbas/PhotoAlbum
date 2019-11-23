package com.selimabbas.photoalbum

import android.app.Application
import com.selimabbas.photoalbum.di.appComponent
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    /**
     * Start koin with appComponent containing modules to load.
     */
    private fun initKoin() {
        startKoin(this, appComponent)
    }
}