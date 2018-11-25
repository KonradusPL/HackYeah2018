package com.konradpekala.hackyeah2018.ui

import android.app.Application
import android.graphics.Color
import es.dmoral.toasty.Toasty

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Toasty.Config.getInstance()
            .setInfoColor(Color.BLACK)
            .apply()
    }
}