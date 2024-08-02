package com.tw.baseproject.app.factories.di

import com.tw.baseproject.app.decorator.DetailMovieDecorator
import com.tw.moviedetail.domain.InsertDetailMovie
import com.tw.moviedetail.domain.LoadDetailMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DecoratorModule{

    @DecoratorAnnotation
    @Provides
    @Singleton
    fun provideDetailMovieDecorator(
        @RemoteUseCaseAnnotation loadDetailMovie: com.tw.moviedetail.domain.LoadDetailMovie,
        @LocalUseCaseAnnotation insertDetailMovie: com.tw.moviedetail.domain.InsertDetailMovie
    ): com.tw.moviedetail.domain.LoadDetailMovie {
        return DetailMovieDecorator(loadDetailMovie, insertDetailMovie)
    }

}