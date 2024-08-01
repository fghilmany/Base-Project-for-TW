package com.tw.baseproject.feature.moviedetail.cacheinfra

import com.tw.shared_resource.ResultData
import com.tw.shared_resource.exception.InvalidDataException
import com.tw.baseproject.feature.moviedetail.cache.DetailMovieLocalClient
import com.tw.baseproject.feature.moviedetail.cache.LocalDetailMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailMovieRoomClient @Inject constructor(
    private val movieDao: DetailMovieDao
): DetailMovieLocalClient {
    override suspend fun saveDetailMovie(detailMovie: LocalDetailMovie) {
        movieDao.insertDetailMovie(detailMovie.toDao())
        movieDao.insertGenre(detailMovie.genres.map { genre -> genre.toDao() })
    }

    override suspend fun getDetailById(movieId: Int): Flow<com.tw.shared_resource.ResultData<LocalDetailMovie>> {
        return flow {
            try {
                val detailMovie = movieDao.getDetailMovieById(movieId).toAppLogic()
                emit(com.tw.shared_resource.ResultData.Success(detailMovie))
            } catch (throwable: Throwable) {
                emit(com.tw.shared_resource.ResultData.Failure(com.tw.shared_resource.exception.InvalidDataException()))
            }
        }
    }

    override suspend fun setDetailMovieFavorite(isFavorite: Boolean, movieId: Int): Flow<com.tw.shared_resource.ResultData<String>> {
        return flow {
            movieDao.setDetailMovieFavorite(isFavorite, movieId)
            emit(com.tw.shared_resource.ResultData.Success(""))
        }
    }

}