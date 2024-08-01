package com.tw.baseproject.feature.movielist.api

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface MoviesHttpClient {
    fun loadMovies(): Flow<com.tw.shared_resource.ResultData<List<RemoteMovie>>>
}