package com.tw.baseproject.app.factories.di

import com.tw.moviedetail.api.DetailMovieHttpClient
import com.tw.moviedetail.api.GetRemoteDetailMovieUseCase
import com.tw.baseproject.feature.moviedetail.cache.DetailMovieLocalClient
import com.tw.baseproject.feature.moviedetail.cache.GetLocalMovieDetailsUseCase
import com.tw.baseproject.feature.moviedetail.cache.InsertDetailMovieUseCase
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
    fun provideLoadMoviesUseCase(client: com.tw.movielist.api.MoviesHttpClient): com.tw.movielist.domain.LoadMovies {
        return com.tw.movielist.api.LoadMoviesUseCase(client)
    }

    @RemoteUseCaseAnnotation
    @Provides
    fun provideLoadDetailMovieUseCase(
        client: com.tw.moviedetail.api.DetailMovieHttpClient
    ): com.tw.moviedetail.domain.LoadDetailMovie {
        return com.tw.moviedetail.api.GetRemoteDetailMovieUseCase(client)
    }

}
@Module
@InstallIn(SingletonComponent::class)
object LocalUseCaseModule {

    @LocalUseCaseAnnotation
    @Provides
    fun provideInsertDetailMovieUseCase(client: DetailMovieLocalClient): com.tw.moviedetail.domain.InsertDetailMovie {
        return InsertDetailMovieUseCase(client)
    }

    @LocalUseCaseAnnotation
    @Provides
    fun provideLoadDetailMovieUseCase(
        client: DetailMovieLocalClient
    ): com.tw.moviedetail.domain.LoadDetailMovie {
        return GetLocalMovieDetailsUseCase(client)
    }
}