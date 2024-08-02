package com.tw.baseproject.factories.di

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
    fun provideDetailMovieDao(database: AppDatabase): DetailMovieDao {
        return database.detailMovieDao()
    }

    @Provides
    fun provideDetailMovieLocalClient(dao: DetailMovieDao): DetailMovieLocalClient {
        return DetailMovieRoomClient(dao)
    }
}
