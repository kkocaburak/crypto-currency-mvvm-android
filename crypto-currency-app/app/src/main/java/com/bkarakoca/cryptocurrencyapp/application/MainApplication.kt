package com.bkarakoca.cryptocurrencyapp.application

import android.app.Application
import com.bkarakoca.cryptocurrencyapp.internal.util.NetworkStateHolder.registerConnectivityMonitor
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        registerConnectivityMonitor()
    }
}