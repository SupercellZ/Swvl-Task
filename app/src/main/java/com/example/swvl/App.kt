package com.example.swvl

import android.app.Application

class App : Application() {

    companion object {
        lateinit var app: App
//        private lateinit var myComponent: MyComponent
    }

    override fun onCreate() {
        super.onCreate()

        app = this
    }
}