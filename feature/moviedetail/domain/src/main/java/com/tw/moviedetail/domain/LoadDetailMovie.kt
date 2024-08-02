package com.tw.moviedetail.domain

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface LoadDetailMovie {
    suspend fun loadDetailMovie(movieId: Int): Flow<ResultData<DetailMovie>>
}