package com.tw.moviedetail.cache

import com.tw.shared_resource.ResultData
import com.tw.shared_resource.exception.DataEmpty
import com.tw.shared_resource.exception.DataEmptyException
import com.tw.shared_resource.exception.InvalidData
import com.tw.shared_resource.exception.InvalidDataException
import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.LoadDetailMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocalMovieDetailsUseCase(
    private val detailMovieLocalClient: DetailMovieLocalClient
): LoadDetailMovie {
    override suspend fun loadDetailMovie(movieId: Int): Flow<ResultData<DetailMovie>> {
        return flow {
            detailMovieLocalClient.getDetailById(movieId)
                .collect{ result ->
                when(result){
                    is ResultData.Success-> {
                        emit(ResultData.Success(result.data.toDomain()))
                    }

                    is ResultData.Failure -> {
                        when(result.throwable){
                            is InvalidDataException -> {
                                emit(ResultData.Failure(InvalidData()))
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
}