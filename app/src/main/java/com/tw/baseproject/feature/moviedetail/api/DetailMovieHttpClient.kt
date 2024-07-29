package com.tw.baseproject.feature.moviedetail.api

import com.tw.baseproject.core.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface DetailMovieHttpClient {
    fun loadDetailMovie(movieId: Int): Flow<ResultData<RemoteDetailMovie>>
}