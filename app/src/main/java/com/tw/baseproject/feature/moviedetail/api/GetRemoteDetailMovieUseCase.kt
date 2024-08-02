package com.tw.baseproject.feature.moviedetail.api

import com.tw.shared_resource.ResultData
import com.tw.shared_resource.exception.Connectivity
import com.tw.shared_resource.exception.ConnectivityException
import com.tw.shared_resource.exception.DataEmpty
import com.tw.shared_resource.exception.DataEmptyException
import com.tw.shared_resource.exception.InvalidData
import com.tw.shared_resource.exception.InvalidDataException
import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.LoadDetailMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRemoteDetailMovieUseCase @Inject constructor(
    private val detailMovieHttpClient: DetailMovieHttpClient
): com.tw.moviedetail.domain.LoadDetailMovie {
    override suspend fun loadDetailMovie(movieId: Int): Flow<com.tw.shared_resource.ResultData<com.tw.moviedetail.domain.DetailMovie>> = flow {
        detailMovieHttpClient.loadDetailMovie(movieId).collect{ result ->
            when(result){
                is com.tw.shared_resource.ResultData.Success-> {
                    emit(com.tw.shared_resource.ResultData.Success(result.data.toDomain()))
                }

                is com.tw.shared_resource.ResultData.Failure -> {
                    when(result.throwable){
                        is com.tw.shared_resource.exception.InvalidDataException -> {
                            emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.InvalidData()))
                        }
                        is com.tw.shared_resource.exception.ConnectivityException -> {
                            emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.Connectivity()))
                        }
                        is com.tw.shared_resource.exception.DataEmptyException -> {
                            emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.DataEmpty()))
                        }
                    }
                }
            }
        }
    }
}