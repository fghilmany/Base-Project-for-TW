package com.tw.baseproject.app.factories.di

import com.tw.baseproject.app.composite.DetailMovieComposite
import com.tw.moviedetail.domain.LoadDetailMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CompositeModule {
    @CompositeAnnotation
    @Provides
    @ViewModelScoped
    fun providesCompositeModule(
        @LocalUseCaseAnnotation primary: com.tw.moviedetail.domain.LoadDetailMovie,
        @DecoratorAnnotation fallback: com.tw.moviedetail.domain.LoadDetailMovie
    ): com.tw.moviedetail.domain.LoadDetailMovie {
        return DetailMovieComposite(primary, fallback)
    }
}