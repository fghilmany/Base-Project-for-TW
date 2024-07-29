package com.tw.baseproject.feature.moviedetail.cache

import com.tw.baseproject.core.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface DetailMovieLocalClient {
    suspend fun saveDetailMovie(detailMovie: LocalDetailMovie)
    suspend fun setDetailMovieFavorite(isFavorite: Boolean, movieId: Int): Flow<ResultData<String>>
    suspend fun getDetailById(movieId: Int): Flow<ResultData<LocalDetailMovie>>
}