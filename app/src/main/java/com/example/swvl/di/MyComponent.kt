package com.example.swvl.di

import com.example.swvl.storage.AppDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MyModule::class
    ]
)
interface MyComponent {

    @Singleton
    fun getAppDatabase(): AppDatabase

}