package com.tw.baseproject.feature.movielist.apiinfra

import com.tw.shared_resource.ResultData
import com.tw.shared_resource.exception.ConnectivityException
import com.tw.shared_resource.exception.DataEmptyException
import com.tw.shared_resource.exception.InvalidDataException
import com.tw.movielist.api.MoviesHttpClient
import com.tw.movielist.api.RemoteMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRetrofitClient @Inject constructor(
    private val movieService: MovieService
): com.tw.movielist.api.MoviesHttpClient {
    override fun loadMovies(): Flow<com.tw.shared_resource.ResultData<List<com.tw.movielist.api.RemoteMovie>>> = flow{
        try {
            val listMovie = movieService.getListMovie().results?.map { it.toAppLogic() }
            if (listMovie != null){
                emit(com.tw.shared_resource.ResultData.Success(listMovie))
            }else{
                emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.DataEmptyException()))
            }
        } catch (throwable: Throwable) {
            when(throwable) {
                is IOException -> {
                    emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.ConnectivityException()))
                }
                is HttpException -> {
                    if (throwable.code() == 422) {
                        emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.InvalidDataException()))
                    }
                }
                else -> {
                    emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.InvalidDataException()))
                }
            }
        }
    }

}