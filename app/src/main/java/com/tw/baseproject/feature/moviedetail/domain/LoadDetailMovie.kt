package com.tw.baseproject.feature.moviedetail.domain

import com.tw.baseproject.core.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface LoadDetailMovie {
    suspend fun loadDetailMovie(movieId: Int): Flow<ResultData<DetailMovie>>
}