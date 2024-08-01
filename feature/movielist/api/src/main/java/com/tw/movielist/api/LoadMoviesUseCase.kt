package com.tw.movielist.api

import com.tw.shared_resource.exception.Connectivity
import com.tw.shared_resource.exception.ConnectivityException
import com.tw.shared_resource.exception.DataEmpty
import com.tw.shared_resource.exception.DataEmptyException
import com.tw.shared_resource.exception.InvalidData
import com.tw.shared_resource.exception.InvalidDataException
import com.tw.shared_resource.ResultData
import com.tw.movielist.domain.LoadMovies
import com.tw.movielist.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val moviesHttpClient: MoviesHttpClient
): LoadMovies {
    override fun loadMovies(): Flow<ResultData<List<Movie>>> = flow {
        moviesHttpClient.loadMovies().collect{ result ->
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