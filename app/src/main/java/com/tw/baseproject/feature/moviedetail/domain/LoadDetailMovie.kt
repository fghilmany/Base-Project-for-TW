package com.tw.baseproject.feature.moviedetail.domain

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface LoadDetailMovie {
    suspend fun loadDetailMovie(movieId: Int): Flow<com.tw.shared_resource.ResultData<DetailMovie>>
}