package com.example.swvl.di

import android.content.Context
import androidx.room.Room
import com.example.swvl.App
import com.example.swvl.storage.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyModule {

    @Provides
    @Singleton
    fun getApplicationContext(): Context = App.app

    @Provides
    @Singleton
    fun provideMyDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "SwvlDB")
            .build()
    }
}