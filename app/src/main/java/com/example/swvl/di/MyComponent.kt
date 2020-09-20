package com.example.swvl.di

import com.example.swvl.data.source.database.AppDatabase
import com.example.swvl.data.source.database.LocalDataSource
import com.example.swvl.data.source.network.RemoteDataSource
import com.example.swvl.data.source.repo.MovieRepo
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
    fun getLocalDataSource(): LocalDataSource

    @Singleton
    fun getRemoteDataSource(): RemoteDataSource

    @Singleton
    fun getMovieRepo() : MovieRepo

}