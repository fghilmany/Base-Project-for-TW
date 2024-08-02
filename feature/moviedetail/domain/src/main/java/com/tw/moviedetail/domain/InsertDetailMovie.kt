package com.tw.moviedetail.domain

import com.tw.shared_resource.ResultData
import kotlinx.coroutines.flow.Flow

interface InsertDetailMovie {
    suspend fun saveDetailMovie(detailMovie: DetailMovie)
    suspend fun setDetailMovieFavorite(isFavorite: Boolean, movieId: Int): Flow<ResultData<String>>
}