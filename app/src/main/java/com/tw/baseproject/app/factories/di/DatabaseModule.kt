package com.tw.baseproject.app.factories.di

import com.tw.room.AppDatabase
import com.tw.moviedetail.cache.DetailMovieLocalClient
import com.tw.moviedetail.cacheinfra.DetailMovieDao
import com.tw.moviedetail.cacheinfra.DetailMovieRoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDetailMovieDao(database: com.tw.room.AppDatabase): com.tw.moviedetail.cacheinfra.DetailMovieDao {
        return database.detailMovieDao()
    }

    @Provides
    fun provideDetailMovieLocalClient(dao: com.tw.moviedetail.cacheinfra.DetailMovieDao): com.tw.moviedetail.cache.DetailMovieLocalClient {
        return com.tw.moviedetail.cacheinfra.DetailMovieRoomClient(dao)
    }
}
