package com.tw.baseproject.factories.di

import com.tw.moviedetail.api.DetailMovieHttpClient
import com.tw.moviedetail.apiinfra.DetailMovieRetrofitClient
import com.tw.moviedetail.apiinfra.DetailMovieService
import com.tw.movielist.api.MoviesHttpClient
import com.tw.movielist.apiinfra.MovieService
import com.tw.movielist.apiinfra.MoviesRetrofitClient
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