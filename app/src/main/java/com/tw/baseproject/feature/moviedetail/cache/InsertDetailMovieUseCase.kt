package com.tw.baseproject.feature.moviedetail.cache

import com.tw.shared_resource.ResultData
import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.InsertDetailMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertDetailMovieUseCase @Inject constructor(
    private val detailMovieLocalClient: DetailMovieLocalClient
): com.tw.moviedetail.domain.InsertDetailMovie {
    override suspend fun saveDetailMovie(detailMovie: com.tw.moviedetail.domain.DetailMovie) {
        return detailMovieLocalClient.saveDetailMovie(detailMovie.toAppLogic())
    }

    override suspend fun setDetailMovieFavorite(
        isFavorite: Boolean,
        movieId: Int
    ): Flow<com.tw.shared_resource.ResultData<String>> {
        return detailMovieLocalClient.setDetailMovieFavorite(isFavorite, movieId)
    }
}