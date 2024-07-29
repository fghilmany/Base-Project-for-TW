package com.tw.baseproject.feature.moviedetail.api

data class RemoteDetailMovie(
    val id: Int,
    val backdropPath: String,
    val title: String,
    val releaseDate: String,
    val genres: List<RemoteGenre>,
    val overview: String
)

data class RemoteGenre(
    val id: Int,
    val genre: String
)
