package com.example.swvl.di

import android.content.Context
import androidx.room.Room
import com.example.swvl.App
import com.example.swvl.data.source.database.AppDatabase
import com.example.swvl.data.source.database.LocalDataSource
import com.example.swvl.data.source.network.MovieService
import com.example.swvl.data.source.network.RemoteDataSource
import com.example.swvl.data.source.repo.MovieRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
/**
 * Dagger Module which specifies how to get each & every required dependency
 */
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

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSource(appDatabase)
    }

    @Provides
    @Singleton
    fun provideMovieService(): MovieService {
        return MovieService.create()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(movieService: MovieService): RemoteDataSource {
        return RemoteDataSource(movieService)
    }

    @Provides
    @Singleton
    fun provideMovieRepo(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): MovieRepo {
        return MovieRepo(localDataSource, remoteDataSource)
    }
}