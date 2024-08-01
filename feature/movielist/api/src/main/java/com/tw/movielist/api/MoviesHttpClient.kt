package com.tw.movielist.api

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface MoviesHttpClient {
    fun loadMovies(): Flow<ResultData<List<RemoteMovie>>>
}