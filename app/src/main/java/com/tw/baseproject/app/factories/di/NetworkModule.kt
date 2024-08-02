package com.tw.baseproject.app.factories.di

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
    fun provideMovieService(retrofit: Retrofit): com.tw.movielist.apiinfra.MovieService {
        return retrofit.create(com.tw.movielist.apiinfra.MovieService::class.java)
    }

    @Provides
    fun provideDetailMovieService(retrofit: Retrofit): com.tw.moviedetail.apiinfra.DetailMovieService {
        return retrofit.create(com.tw.moviedetail.apiinfra.DetailMovieService::class.java)
    }

    @Provides
    fun provideMoviesHttpClient(service: com.tw.movielist.apiinfra.MovieService): com.tw.movielist.api.MoviesHttpClient {
        return com.tw.movielist.apiinfra.MoviesRetrofitClient(service)
    }

    @Provides
    fun provideDetailMovieHttpClient(service: com.tw.moviedetail.apiinfra.DetailMovieService): com.tw.moviedetail.api.DetailMovieHttpClient {
        return com.tw.moviedetail.apiinfra.DetailMovieRetrofitClient(service)
    }
}