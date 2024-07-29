package com.tw.baseproject.feature.moviedetail.domain

import com.tw.baseproject.core.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface InsertDetailMovie {
    suspend fun saveDetailMovie(detailMovie: DetailMovie)
    suspend fun setDetailMovieFavorite(isFavorite: Boolean, movieId: Int): Flow<ResultData<String>>
}