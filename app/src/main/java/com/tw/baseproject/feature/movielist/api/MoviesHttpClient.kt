package com.tw.baseproject.feature.movielist.api

import com.tw.baseproject.core.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface MoviesHttpClient {
    fun loadMovies(): Flow<ResultData<List<RemoteMovie>>>
}