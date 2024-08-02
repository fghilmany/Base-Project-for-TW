package com.tw.baseproject.app.decorator

import com.tw.shared_resource.ResultData
import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.InsertDetailMovie
import com.tw.moviedetail.domain.LoadDetailMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailMovieDecorator @Inject constructor(
    private val decorator: com.tw.moviedetail.domain.LoadDetailMovie,
    private val local: com.tw.moviedetail.domain.InsertDetailMovie
): com.tw.moviedetail.domain.LoadDetailMovie {
    override suspend fun loadDetailMovie(movieId: Int): Flow<com.tw.shared_resource.ResultData<com.tw.moviedetail.domain.DetailMovie>> {
        return flow {
            decorator.loadDetailMovie(movieId).collect{ response ->
                if(response is com.tw.shared_resource.ResultData.Success)
                    local.saveDetailMovie(response.data)
                emit(response)
            }
        }
    }
}