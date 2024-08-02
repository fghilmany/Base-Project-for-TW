package com.tw.baseproject.factories.di

import com.tw.moviedetail.api.DetailMovieHttpClient
import com.tw.moviedetail.api.GetRemoteDetailMovieUseCase
import com.tw.moviedetail.cache.DetailMovieLocalClient
import com.tw.moviedetail.cache.GetLocalMovieDetailsUseCase
import com.tw.moviedetail.cache.InsertDetailMovieUseCase
import com.tw.moviedetail.domain.InsertDetailMovie
import com.tw.moviedetail.domain.LoadDetailMovie
import com.tw.movielist.api.LoadMoviesUseCase
import com.tw.movielist.api.MoviesHttpClient
import com.tw.movielist.domain.LoadMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteUseCaseModule {

    @Provides
    fun provideLoadMoviesUseCase(client: MoviesHttpClient): LoadMovies {
        return LoadMoviesUseCase(client)
    }

    @RemoteUseCaseAnnotation
    @Provides
    fun provideLoadDetailMovieUseCase(
        client: DetailMovieHttpClient
    ): LoadDetailMovie {
        return GetRemoteDetailMovieUseCase(client)
    }

}
@Module
@InstallIn(SingletonComponent::class)
object LocalUseCaseModule {

    @LocalUseCaseAnnotation
    @Provides
    fun provideInsertDetailMovieUseCase(client: DetailMovieLocalClient): InsertDetailMovie {
        return InsertDetailMovieUseCase(client)
    }

    @LocalUseCaseAnnotation
    @Provides
    fun provideLoadDetailMovieUseCase(
        client: DetailMovieLocalClient
    ): LoadDetailMovie {
        return GetLocalMovieDetailsUseCase(client)
    }
}