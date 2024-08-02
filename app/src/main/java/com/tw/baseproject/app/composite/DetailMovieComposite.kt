package com.tw.baseproject.app.composite

import com.tw.shared_resource.ResultData
import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.LoadDetailMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class DetailMovieComposite(
    private val primary: com.tw.moviedetail.domain.LoadDetailMovie,
    private val fallback: com.tw.moviedetail.domain.LoadDetailMovie
): com.tw.moviedetail.domain.LoadDetailMovie {
    override suspend fun loadDetailMovie(movieId: Int): Flow<com.tw.shared_resource.ResultData<com.tw.moviedetail.domain.DetailMovie>> = flow {
        primary.loadDetailMovie(movieId).collect { result ->
            when(result){
                is com.tw.shared_resource.ResultData.Success -> emit(result)
                is com.tw.shared_resource.ResultData.Failure -> emit(fallback.loadDetailMovie(movieId).first())
            }
        }
    }
}