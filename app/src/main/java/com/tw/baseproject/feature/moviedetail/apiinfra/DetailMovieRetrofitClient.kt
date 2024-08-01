package com.tw.baseproject.feature.moviedetail.apiinfra

import com.tw.shared_resource.ResultData
import com.tw.shared_resource.exception.ConnectivityException
import com.tw.shared_resource.exception.InvalidDataException
import com.tw.baseproject.feature.moviedetail.api.DetailMovieHttpClient
import com.tw.baseproject.feature.moviedetail.api.RemoteDetailMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailMovieRetrofitClient @Inject constructor(
    private val detailMovieService: DetailMovieService
): DetailMovieHttpClient {
    override fun loadDetailMovie(movieId: Int): Flow<com.tw.shared_resource.ResultData<RemoteDetailMovie>> = flow{
        try {
            val listMovie = detailMovieService.getDetailMovie(movieId).toAppLogic()
            emit(com.tw.shared_resource.ResultData.Success(listMovie))
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
    }.flowOn(Dispatchers.IO)

}