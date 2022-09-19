package io.vishpraveen.demoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}