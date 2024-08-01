package com.tw.baseproject.app.factories.di

import com.tw.room.AppDatabase
import com.tw.baseproject.feature.moviedetail.cache.DetailMovieLocalClient
import com.tw.baseproject.feature.moviedetail.cacheinfra.DetailMovieDao
import com.tw.baseproject.feature.moviedetail.cacheinfra.DetailMovieRoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDetailMovieDao(database: com.tw.room.AppDatabase): DetailMovieDao {
        return database.detailMovieDao()
    }

    @Provides
    fun provideDetailMovieLocalClient(dao: DetailMovieDao): DetailMovieLocalClient {
        return DetailMovieRoomClient(dao)
    }
}
