package com.tw.movielist.domain

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow


interface LoadMovies{
    fun loadMovies(): Flow<ResultData<List<Movie>>>
}

