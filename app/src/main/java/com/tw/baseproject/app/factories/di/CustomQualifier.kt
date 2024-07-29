package com.tw.baseproject.app.factories.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteUseCaseAnnotation
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalUseCaseAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CompositeAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DecoratorAnnotation
