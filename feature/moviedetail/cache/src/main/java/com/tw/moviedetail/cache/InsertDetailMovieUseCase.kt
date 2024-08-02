package com.tw.moviedetail.cache

import com.tw.shared_resource.ResultData
import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.InsertDetailMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertDetailMovieUseCase @Inject constructor(
    private val detailMovieLocalClient: DetailMovieLocalClient
): InsertDetailMovie {
    override suspend fun saveDetailMovie(detailMovie: DetailMovie) {
        return detailMovieLocalClient.saveDetailMovie(detailMovie.toAppLogic())
    }

    override suspend fun setDetailMovieFavorite(
        isFavorite: Boolean,
        movieId: Int
    ): Flow<ResultData<String>> {
        return detailMovieLocalClient.setDetailMovieFavorite(isFavorite, movieId)
    }
}