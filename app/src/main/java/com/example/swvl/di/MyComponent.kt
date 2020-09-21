package com.example.swvl.di

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
/**
 * Dagger Component which exposes methods to access all sorts of objects anywhere in the app
 */
interface MyComponent {


    @Singleton
    fun getLocalDataSource(): LocalDataSource

    @Singleton
    fun getRemoteDataSource(): RemoteDataSource

    @Singleton
    fun getMovieRepo(): MovieRepo

}