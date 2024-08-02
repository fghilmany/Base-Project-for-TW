package com.tw.moviedetail.cache

data class LocalDetailMovie(
    val id: Int,
    val backdropPath: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
    var isFavorite: Boolean = false,
    val genres: List<LocalGenre>,
)

data class LocalGenre(
    val id: Int,
    val movieId: Int,
    val name: String
)