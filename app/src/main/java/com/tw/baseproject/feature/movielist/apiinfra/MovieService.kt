package com.tw.baseproject.feature.movielist.apiinfra

import retrofit2.http.GET

interface MovieService {

    @GET("discover/movie")
    suspend fun getListMovie(): MovieResponse
}