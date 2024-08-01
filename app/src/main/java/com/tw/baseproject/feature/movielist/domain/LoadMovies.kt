package com.tw.baseproject.feature.movielist.domain

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow


interface LoadMovies{
    fun loadMovies(): Flow<com.tw.shared_resource.ResultData<List<Movie>>>
}

