package com.tw.baseproject.app.factories.di

import com.tw.baseproject.feature.moviedetail.api.DetailMovieHttpClient
import com.tw.baseproject.feature.moviedetail.api.GetRemoteDetailMovieUseCase
import com.tw.baseproject.feature.moviedetail.cache.DetailMovieLocalClient
import com.tw.baseproject.feature.moviedetail.cache.GetLocalMovieDetailsUseCase
import com.tw.baseproject.feature.moviedetail.cache.InsertDetailMovieUseCase
import com.tw.baseproject.feature.moviedetail.domain.InsertDetailMovie
import com.tw.baseproject.feature.moviedetail.domain.LoadDetailMovie
import com.tw.baseproject.feature.movielist.api.LoadMoviesUseCase
import com.tw.baseproject.feature.movielist.api.MoviesHttpClient
import com.tw.movielist.domain.LoadMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteUseCaseModule {

    @Provides
    fun provideLoadMoviesUseCase(client: MoviesHttpClient): com.tw.movielist.domain.LoadMovies {
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
    ): LoadDetailMovie{
        return GetLocalMovieDetailsUseCase(client)
    }
}