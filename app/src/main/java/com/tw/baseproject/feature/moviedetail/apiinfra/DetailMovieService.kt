package com.tw.baseproject.feature.moviedetail.apiinfra

import retrofit2.http.GET
import retrofit2.http.Path

interface DetailMovieService {

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
       @Path("movie_id") movieId: Int
    ): DetailMovieResponse
}