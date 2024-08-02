package com.tw.moviedetail.api

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface DetailMovieHttpClient {
    fun loadDetailMovie(movieId: Int): Flow<ResultData<RemoteDetailMovie>>
}