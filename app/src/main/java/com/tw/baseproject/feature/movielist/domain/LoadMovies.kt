package com.tw.baseproject.feature.movielist.domain

import com.tw.baseproject.core.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow


interface LoadMovies{
    fun loadMovies(): Flow<ResultData<List<Movie>>>
}

