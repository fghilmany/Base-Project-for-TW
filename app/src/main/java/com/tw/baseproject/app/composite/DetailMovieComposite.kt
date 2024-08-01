package com.tw.baseproject.app.composite

import com.tw.shared_resource.ResultData
import com.tw.baseproject.feature.moviedetail.domain.DetailMovie
import com.tw.baseproject.feature.moviedetail.domain.LoadDetailMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class DetailMovieComposite(
    private val primary: LoadDetailMovie,
    private val fallback: LoadDetailMovie
): LoadDetailMovie {
    override suspend fun loadDetailMovie(movieId: Int): Flow<com.tw.shared_resource.ResultData<DetailMovie>> = flow {
        primary.loadDetailMovie(movieId).collect { result ->
            when(result){
                is com.tw.shared_resource.ResultData.Success -> emit(result)
                is com.tw.shared_resource.ResultData.Failure -> emit(fallback.loadDetailMovie(movieId).first())
            }
        }
    }
}