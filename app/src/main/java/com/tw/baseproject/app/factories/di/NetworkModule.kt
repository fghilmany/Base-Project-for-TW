package com.tw.baseproject.app.factories.di

import com.tw.baseproject.feature.moviedetail.api.DetailMovieHttpClient
import com.tw.baseproject.feature.moviedetail.apiinfra.DetailMovieRetrofitClient
import com.tw.baseproject.feature.moviedetail.apiinfra.DetailMovieService
import com.tw.baseproject.feature.movielist.api.MoviesHttpClient
import com.tw.baseproject.feature.movielist.apiinfra.MovieService
import com.tw.baseproject.feature.movielist.apiinfra.MoviesRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    fun provideDetailMovieService(retrofit: Retrofit): DetailMovieService {
        return retrofit.create(DetailMovieService::class.java)
    }

    @Provides
    fun provideMoviesHttpClient(service: MovieService): MoviesHttpClient {
        return MoviesRetrofitClient(service)
    }

    @Provides
    fun provideDetailMovieHttpClient(service: DetailMovieService): DetailMovieHttpClient {
        return DetailMovieRetrofitClient(service)
    }
}