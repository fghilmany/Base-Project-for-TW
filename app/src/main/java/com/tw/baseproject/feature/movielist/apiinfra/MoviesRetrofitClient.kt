package com.tw.baseproject.feature.movielist.apiinfra

import com.tw.baseproject.core.shared_resource.ResultData
import com.tw.baseproject.core.shared_resource.exception.ConnectivityException
import com.tw.baseproject.core.shared_resource.exception.DataEmptyException
import com.tw.baseproject.core.shared_resource.exception.InvalidDataException
import com.tw.baseproject.feature.movielist.api.MoviesHttpClient
import com.tw.baseproject.feature.movielist.api.RemoteMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRetrofitClient @Inject constructor(
    private val movieService: MovieService
): MoviesHttpClient {
    override fun loadMovies(): Flow<ResultData<List<RemoteMovie>>> = flow{
        try {
            val listMovie = movieService.getListMovie().results?.map { it.toAppLogic() }
            if (listMovie != null){
                emit(ResultData.Success(listMovie))
            }else{
                emit(ResultData.Failure(DataEmptyException()))
            }
        } catch (throwable: Throwable) {
            when(throwable) {
                is IOException -> {
                    emit(ResultData.Failure(ConnectivityException()))
                }
                is HttpException -> {
                    if (throwable.code() == 422) {
                        emit(ResultData.Failure(InvalidDataException()))
                    }
                }
                else -> {
                    emit(ResultData.Failure(InvalidDataException()))
                }
            }
        }
    }

}