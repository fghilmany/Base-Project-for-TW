package com.tw.moviedetail.api

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
): LoadDetailMovie {
    override suspend fun loadDetailMovie(movieId: Int): Flow<ResultData<DetailMovie>> = flow {
        detailMovieHttpClient.loadDetailMovie(movieId).collect{ result ->
            when(result){
                is ResultData.Success-> {
                    emit(ResultData.Success(result.data.toDomain()))
                }

                is ResultData.Failure -> {
                    when(result.throwable){
                        is InvalidDataException -> {
                            emit(ResultData.Failure(InvalidData()))
                        }
                        is ConnectivityException -> {
                            emit(ResultData.Failure(Connectivity()))
                        }
                        is DataEmptyException -> {
                            emit(ResultData.Failure(DataEmpty()))
                        }
                    }
                }
            }
        }
    }
}