package com.tw.baseproject.factories.di

import com.tw.moviedetail.domain.LoadDetailMovie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule{
    @Binds
    @ViewModelScoped
    abstract fun provideLoadDetailMovie(
        @CompositeAnnotation loadDetailMovie: LoadDetailMovie
    ): LoadDetailMovie

}
