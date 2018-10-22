package com.heshucheng.graduation.utiles

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

    companion object {
        var context: Context? = null
            private set
    }

}