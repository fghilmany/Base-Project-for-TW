package com.tw.baseproject.decorator

import com.tw.shared_resource.ResultData
import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.InsertDetailMovie
import com.tw.moviedetail.domain.LoadDetailMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailMovieDecorator @Inject constructor(
    private val decorator: LoadDetailMovie,
    private val local: InsertDetailMovie
): LoadDetailMovie {
    override suspend fun loadDetailMovie(movieId: Int): Flow<ResultData<DetailMovie>> {
        return flow {
            decorator.loadDetailMovie(movieId).collect{ response ->
                if(response is ResultData.Success)
                    local.saveDetailMovie(response.data)
                emit(response)
            }
        }
    }
}