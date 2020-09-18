package com.example.swvl

import android.app.Application
import com.example.swvl.di.DaggerMyComponent
import com.example.swvl.di.MyComponent

class App : Application() {

    companion object {
        lateinit var app: App
        private lateinit var myComponent: MyComponent
    }

    override fun onCreate() {
        super.onCreate()

        app = this
        myComponent = createMyComponent()
    }

    fun getMyComponent(): MyComponent {
        return myComponent
    }

    private fun createMyComponent(): MyComponent {
        return DaggerMyComponent
            .builder()
            .build()
    }
}