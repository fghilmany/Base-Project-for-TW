package com.tw.baseproject.feature.moviedetail.apiinfra

import com.tw.baseproject.core.shared_resource.ResultData
import com.tw.baseproject.core.shared_resource.exception.ConnectivityException
import com.tw.baseproject.core.shared_resource.exception.InvalidDataException
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
    override fun loadDetailMovie(movieId: Int): Flow<ResultData<RemoteDetailMovie>> = flow{
        try {
            val listMovie = detailMovieService.getDetailMovie(movieId).toAppLogic()
            emit(ResultData.Success(listMovie))
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
    }.flowOn(Dispatchers.IO)

}